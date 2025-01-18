package com.uptoser.java.javase.jdk8.collecting;

import com.uptoser.java.javase.jdk8.Dish;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


/**
 * 收集器用作高级归约
 *
 */
public class Application {

    //菜肴列表
    static List<Dish> menu = asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    /**
     * 归约和汇总
     */
    @Test
    public void test1(){
        //数一数菜单里有多少种菜
        long howManyDishes = menu.stream().collect(Collectors.counting());
        //这还可以写得更为直接
        howManyDishes = menu.stream().count();
        System.out.println(howManyDishes);
        //查找流中的最大值和最小值
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        mostCalorieDish = menu.stream().max(dishCaloriesComparator);
        mostCalorieDish.ifPresent(System.out::println);
        //汇总单值   summingLong  averagingInt
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(totalCalories);
        //获取全部收集的值 : 找到元素数值属性的最大值和最小值，以及计算其总和和平均值
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
        //连接字符串 joining在内部使用了StringBuilder来把生成的字符串逐个追加起来
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
        System.out.println(shortMenu);
        System.out.println("----------------------------------------------------------------");
        /*
        广义的归约汇总
        所有收集器，都是一个可以用reducing工厂方法定义的归约过程的特殊情况而已
        Collectors.reducing需要三个参数
        第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值和而言0是一个合适的值。
        第二个参数就是使用的函数，将菜肴转换成一个表示其所含热量的int。
        第三个参数是一个BinaryOperator，将两个项目累积成一个同类型的值。这里它就是对两个int求和。
         */
        int totalCalories1 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        totalCalories1 = menu.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j);
        System.out.println(totalCalories1);
        //同样，你可以使用下面这样单参数形式的reducing来找到热量最高的菜，如下所示：
        Optional<Dish> mostCalorieDish1 = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        mostCalorieDish1 = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
        System.out.println(mostCalorieDish1.get());
    }

    /**
     * 分组
     */
    @Test
    public void test2(){
        //分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(dishesByType);
        /*
        在分组过程中对流中的项目进行分类
        你可能想把热量不到400卡路里的菜划为“低热量”（diet），
        把热量在400到700卡路里之间的菜划为“普通”（normal），
        而把高于700卡路里的菜划为“高热量”（fat）。
        由于Dish类的作者没有把这个操作写成一个方法，因此无法使用方法引用，但你可以把这个逻辑写成Lambda表达式：
         */
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                } ));
        System.out.println(dishesByCaloricLevel);
        System.out.println("----------------------------------");
        //按照菜肴的热量进行过滤操作 filtering
        Map<Dish.Type, List<Dish>> caloricDishesByType =
                menu.stream().filter(dish -> dish.getCalories() > 500)
                        .collect(groupingBy(Dish::getType));
        System.out.println(caloricDishesByType);
        caloricDishesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println(caloricDishesByType);
        System.out.println("----------------------------------");
        //通过mapping方法 它接受一个映射函数和另一个Collector函数作为参数
        Map<Dish.Type, List<Integer>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                mapping(Dish::getCalories, toList())));
        System.out.println(dishNamesByType);
        System.out.println("----------------------------------");
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));
        //如果你需要提取出每组菜肴对应的标签，使用flatMapping Collector可以轻松实现：
        Map<Dish.Type, Set<String>> dishNamesByType3 =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
                                        toSet())));
        System.out.println(dishNamesByType3);
        System.out.println("----------------------------------");
        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,  // 一级分类函数
                        groupingBy(dish -> {  // 二级分类函数
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                )
        );
        System.out.println(dishesByTypeCaloricLevel);
        //按子组收集数据 数一数菜单中每类菜有多少个，可以传递counting收集器作为groupingBy收集器的第二个参数
        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);
        //把收集器的结果转换为另一种类型,你可以使用Collectors.collectingAndThen工厂方法返回的收集器
        //因为分组操作的Map结果中的每个值上包装的Optional没什么用，所以你可能想要把它们去掉
        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,  //分类函数
                                collectingAndThen(
                                        maxBy(comparingInt(Dish::getCalories)), // 包装后的收集器
                                Optional::get))); // 转换函数
        System.out.println(mostCaloricByType);
        //求出所有菜肴热量总和的收集器，不过这次是对每一组Dish求和
        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);
    }

    /**
     * 分区
     * 分区是分组的特殊情况：由一个谓词（返回一个布尔值的函数）作为分类函数，它称分区函数
     * 它最多可以分为两组——true是一组，false是一组
     */
    public static void main(String[] args) {
        //分区
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));
        System.out.println(mostCaloricPartitionedByVegetarian);
    }


}
