package com.uptoser.java_learning.java8.c.collection;

import com.uptoser.java_learning.java8.b.stream.Dish;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


//流
public class Application {

    private Application() {
    }

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

    public static void main(String[] args) {
        //查找流中的最大值和最小值
        Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        mostCalorieDish.ifPresent(System.out::println);

        //汇总单值   summingLong  averagingInt
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        //获取全部收集的值
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        //连接字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(joining(","));
//        shortMenu = menu.stream().collect(joining()); //如果Dish类有一个toString方法来返回菜肴的名称，那你无需用提取每一道菜名称的函数来对原流做映射就能够得到相同的结果
        System.out.println(shortMenu);

        //广义的归约汇总
//        第一个参数是归约操作的起始值，也是流中没有元素时的返回值，所以很显然对于数值和而言0是一个合适的值。
//        第二个参数就是上一节中使用的函数，将菜肴转换成一个表示其所含热量的int。
//        第三个参数是一个BinaryOperator，将两个项目累积成一个同类型的值。这里它就是对两个int求和。
        int totalCalories1 = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        //同样，你可以使用下面这样单参数形式的reducing来找到热量最高的菜，如下所示：
        Optional<Dish> mostCalorieDish1 = menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

        //分组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        //在分组过程中对流中的项目进行分类
        //但是，分类函数不一定像方法引用那样可用，因为你想用以分类的条件可能比简单的属性访问器要复杂。例如，你可能想把热量不到400卡路里的菜划为“低热量”（diet），把热量在400到700卡路里之间的菜划为“普通”（normal），而把高于700卡路里的菜划为“高热量”（fat）。由于Dish类的作者没有把这个操作写成一个方法，因此无法使用方法引用，但你可以把这个逻辑写成Lambda表达式：

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                } ));

        //按照菜肴的热量进行过滤操作
//        Map<Dish.Type, List<Dish>> caloricDishesByType =
//                menu.stream()
//                        .collect(groupingBy(Dish::getType,
//                                filtering(dish -> dish.getCalories() > 500, toList())));

        Map<Dish.Type, List<Integer>> dishNamesByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                mapping(Dish::getCalories, toList())));
        System.out.println(dishNamesByType);
        //------------------------------------------------------
//        Map<String, List<String>> dishTags = new HashMap<>();
//        dishTags.put("pork", asList("greasy", "salty"));
//        dishTags.put("beef", asList("salty", "roasted"));
//        dishTags.put("chicken", asList("fried", "crisp"));
//        dishTags.put("french fries", asList("greasy", "fried"));
//        dishTags.put("rice", asList("light", "natural"));
//        dishTags.put("season fruit", asList("fresh", "natural"));
//        dishTags.put("pizza", asList("tasty", "salty"));
//        dishTags.put("prawns", asList("tasty", "roasted"));
//        dishTags.put("salmon", asList("delicious", "fresh"));
//        //如果你需要提取出每组菜肴对应的标签，使用flatMapping Collector可以轻松实现：
//        Map<Dish.Type, Set<String>> dishNamesByType3 =
//                menu.stream()
//                        .collect(groupingBy(Dish::getType,
//                                flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
//                                        toSet())));

        //分区
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,
                                collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                                        Optional::get)));




    }
    public enum CaloricLevel { DIET, NORMAL, FAT }

}
