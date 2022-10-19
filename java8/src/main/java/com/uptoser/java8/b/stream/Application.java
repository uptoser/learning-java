package com.uptoser.java8.b.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


//流
public class Application {

    private Application() {
    }

    //菜肴列表
    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        /*
         第一个流操作  筛选（filter）、提取（map）或截断（limit）
         */
        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());
        System.out.println(threeHighCaloricDishNames);
        System.out.println("--------------------------------------------------------");
        /*
        流只能消费一次
         */
        List<String> title = Arrays.asList("Modern", "Java", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
//        s.forEach(System.out::println);//这里会抛一个异常

        System.out.println("--------------------------------------------------------");
        /*
        中间操作 filter map limit sorted distinct 终端操作 forEach count collect
         */
        List<String> names =
                menu.stream()
                        .filter(dish -> {
                            System.out.println("filtering:" + dish.getName());
                            return dish.getCalories() > 300;
                        })
                        .map(dish -> {
                            System.out.println("mapping:" + dish.getName());
                            return dish.getName();
                        })
                        .limit(3)
                        .collect(toList());
        System.out.println(names);//1.尽管很多菜的热量都高于300卡路里，但只选出了前三个(短路)2.尽管filter和map是两个独立的操作，但它们合并到同一次遍历中了(循环合并)
        System.out.println("--------------------------------------------------------");
        /*
        distinct 它会返回一个元素各异   确保没有重复
         */
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
        /*
        使用谓词对流进行切片
        jdk 9  takeWhile和dropWhile
         */
        List<Dish> dishes1 = menu
                .stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)//该方法只选出了符合谓词的头三个元素
                .collect(toList());

        List<Dish> dishes2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)//跳过热量超过300卡路里的头两道菜
                .collect(toList());
        dishes2.forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
         /*
        映射
         */
        // 流的扁平化 flatMap
        List<String> words = Arrays.asList("Hello", "World");
        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList()).forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
        //练习：给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？例如，给定[1, 2, 3, 4, 5]，应该返回[1, 4, 9, 16, 25]。
        Arrays.asList(1, 2, 3, 4, 5).stream().map(i -> i * i).collect(toList()).forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
        //练习：给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3),(3, 4)]
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3, 4);
        integers1.stream().flatMap(integer1 -> integers2.stream().map(integer2 -> "(" + integer1 + "," + integer2 + ")"))
                .collect(toList()).forEach(System.out::println);
        System.out.println("--------------------------------------------------------");
         /*
        查找和匹配  allMatch、anyMatch、noneMatch、findFirst和findAny
         */
        //检查谓词是否至少匹配一个元素 菜单里面是否有素食
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        System.out.println("--------------------------------------------------------");
        //检查谓词是否匹配所有元素
        boolean isHealthy1 = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        //确保流中没有任何元素与给定的谓词匹配
        boolean isHealthy2 = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

        //查找元素
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent(d -> System.out.println(d.getName()));
        //为什么会同时有findFirst和findAny呢？答案是并行。找到第一个元素在并行上限制更多。如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流时限制较少。
        menu.stream().filter(d -> d.getCalories() > 500).findFirst().ifPresent(System.out::println);
        System.out.println("--------------------------------------------------------");

        /*
        归约 reduce   将流中所有元素反复结合起来，得到一个值 这样的查询可以被归类为归约操作（将流归约成一个值） 用函数式编程语言的术语来说，这称为折叠（fold）
         */
        //元素求和
        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);
        sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("求和：" + sum);
        System.out.println("--------------------------------------------------------");
        //计算最大值
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);
        System.out.println("--------------------------------------------------------");
        //练习：用map和reduce方法数一数流中有多少个菜呢？
        menu.stream().map(v -> 1).reduce(Integer::sum).ifPresent(System.out::println);
        System.out.println("--------------------------------------------------------");
        /*
         * 大练习
         */
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //找出2011年发生的所有交易，并按交易额排序（从低到高）
        transactions.stream().filter(t -> t.getYear() == 2011).sorted(comparing(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println("------------");
        //交易员都在哪些不同的城市工作过
        transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);
        System.out.println("------------");
        //查找所有来自于剑桥的交易员，并按姓名排序
        transactions.stream().map(Transaction::getTrader).filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(comparing(Trader::getName)).forEach(System.out::println);
        System.out.println("------------");
        //返回所有交易员的姓名字符串，按字母顺序排序
        String reduce = transactions.stream().map(t -> t.getTrader().getName())
                .distinct()
                .sorted().reduce("", (n1, n2) -> n1 + " " + n2);
        System.out.println(reduce);
        System.out.println("------------");

        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(joining());
        //有没有交易员是在米兰工作的
        boolean milan = transactions.stream().anyMatch(transaction -> transaction.getTrader()
                .getCity()
                .equals("Milan"));
        if (milan) System.out.println("有在米兰工作的");
        else System.out.println("没有在米兰工作的");
        System.out.println("------------");
        //打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println("------------");
        //所有交易中，最高的交易额是多少
        transactions.stream().map(Transaction::getValue).reduce(Integer::max).ifPresent(System.out::println);
        System.out.println("------------");
        //找到交易额最小的交易
        transactions.stream().reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2).ifPresent(System.out::println);
        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .min(comparing(Transaction::getValue));//流支持min和max方法
        /*
        数值流
         */
        //原始类型流特化  IntStream、DoubleStream和LongStream / mapToInt、mapToDouble和mapToLong
        //默认值OptionalInt    对于三种原始流特化，也分别有一个Optional原始类型特化版本：OptionalInt、OptionalDouble和OptionalLong。
        int calories = menu.stream()       //返回一个Stream<Dish>
                .mapToInt(Dish::getCalories)       // 返回一个IntStream
                .sum();
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);      //将Stream转换为数值流
        Stream<Integer> stream = intStream.boxed();       // 将数值流转换为Stream

        //数值范围  range和rangeClosed。这两个方法都是第一个参数接受起始值，第二个参数接受结束值。但range是不包含结束值的，rangeClosed则包含结束值
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)       //表示范围[1, 100]
                .filter(n -> n % 2 == 0);       // 一个从1到100的偶数流
        System.out.println(evenNumbers.count());      // 从1到100有50个偶数
        System.out.println("---------------------------------------------------------");
        /*
        构建流
         */
        //由值创建流
        Stream<String> stream1 = Stream.of("Modern ", "Java ", "In ", "Action");
        stream1.map(String::toUpperCase).forEach(System.out::println);
        //由数组创建流
        int[] numbers1 = {2, 3, 5, 7, 11, 13};
        int sum1 = Arrays.stream(numbers1).sum();      // 总和是41
        System.out.println("---------------------------------------------------------");
        //由文件生成流
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("src","data.txt"), Charset.defaultCharset())) {       // 流会自动关闭，因此不需要执行额外的try-finally操作
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))      // 生成单词流
                    .distinct()      // 删除重复项
                    .count();      // 数一数有多少不重复的单词
        } catch (IOException e) {
            // 如果打开文件时出现异常则加以处理
        }
        System.out.println(uniqueWords);
        System.out.println("---------------------------------------------------------");

        //由函数生成流：创建无限流
        Stream.iterate(0, n -> n + 2)//iterate方法接受一个初始值（在这里是0），还有一个依次应用在每个产生的新值上的Lambda（UnaryOperator<t>类型）
                .limit(10)
                .forEach(System.out::println);
        System.out.println("---------------------------------------------------------");

        //测验：生成斐波那契元组序列中的前20个元素。
        Stream.iterate(new int[]{0, 1}, a -> new int[]{a[1], a[0] + a[1]}).limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
        System.out.println("---------------------------------------------------------");
        //generate  它接受一个Supplier<T>类型的Lambda提供新的值
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);



    }


}
