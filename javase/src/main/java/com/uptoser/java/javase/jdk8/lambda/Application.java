package com.uptoser.java.javase.jdk8.lambda;

import com.uptoser.java.javase.jdk8.Dish;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

//基础与Lambda表达式
public class Application {


    /**
     * Java 8中的常用函数式接口
     * Predicate<T>           T -> boolean
     * Consumer<T>            T -> void
     * Function<T, R>         T -> R
     * Supplier<T>            () -> T
     * UnaryOperator<T>       T -> T
     * BinaryOperator<T>      (T, T) -> T
     * BiPredicate<T, U>      (T, U) -> boolean
     * BiConsumer<T, U>       (T, U) -> void
     * BiFunction<T, U, R>    (T, U) -> R
     */
    public static void main(String[] args) {
        /* *****************常用函数式接口********************* */
        //谓词 下面代码等价于 (String s) -> "1".equals(s)
        Predicate<String> predicate = "1"::equals;
        //消费者 下面代码等价于(String s) -> System.out.println(s)
        Consumer consumer = System.out::println;
        //函数 下面代码等价于 (Apple apple) -> apple.getWeight()
        Function<Apple, Integer> function = Apple::getWeight;
        //提供者 下面代码等价于 new Apple()
        Supplier<Apple> supplier = Apple::new;
        //比较器 下面代码等价于(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);

        /* *******************方法引用******************* */
        //静态方法的方法引用 (String s) -> Integer.parseInt(s)
        Function<String, Integer> function1 = Integer::parseInt;
        ToIntFunction<String> stringToInt = Integer::parseInt;
        //任意类型实例方法的方法引用  (String s) -> s.length()
        Function<String, Integer> function2 = String::length;
        //现存对象或表达式实例方法的方法引用 (String s) -> out.println(s)
        PrintStream out = System.out;
        Consumer consumer1 = out::println;

        /* *******************构造函数引用******************* */
        //构造函数没有参数
        Supplier<Apple> c1 = Apple::new;//构造函数引用指向默认的Apple()构造函数
        Apple a1 = c1.get();// 调用Supplier的get方法将产生一个新的Apple
        //构造函数的签名是Apple(Integer weight)
        Function<Integer, Apple> c2 = Apple::new; //指向Apple(Integer weight)的构造函数引用
        Apple a2 = c2.apply(110);//调用该Function函数的apply方法，并给出要求的重量，将产生一个Apple
        //三个参数的构造器(需要自定义函数式接口)
        TriFunction<Integer, Color, String, Apple> a3 = Apple::new;

        /* *******************复合Lambda表达式******************* */
        /*
        比较器复合
         */
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        //逆序
        Comparator<Apple> reversed = c.reversed();//按重量递减排序
        //比较器链 (按重量递减排序 --> 两个苹果一样重时，进一步按国家排序)
        Comparator<Apple> appleComparator = reversed.thenComparing(Apple::getCountry);
        /*
        谓词复合
        谓词接口包括三个方法：negate、and和or
        and和or方法是按照在表达式链中的位置，从左向右确定优先级的。
         */
        Predicate<Apple> redApple = apple -> Color.Red == apple.getColor();
        //非
        Predicate<Apple> notRedApple = redApple.negate();
        //与
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        //或
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150)
                .or(apple -> Color.Green == apple.getColor()); //链接三个谓词
        /*
        函数复合
         */
        //数学上会写作g(f(x))或(g o f)(x)
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 2;
        Function<Integer, Integer> h1 = f1.andThen(g1);
        //数学上会写作f(g(x))或(f o g)(x)
        Function<Integer, Integer> f2 = x -> x + 1;
        Function<Integer, Integer> g2 = x -> x * 2;
        Function<Integer, Integer> h2 = f2.compose(g2);

        /* *******************数学中的类似思想******************* */
    }
    @Test
    public void listToMap(){
        List<Dish> menu = asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        Map<Integer, Dish> collect = menu.stream()
                .collect(Collectors
                        .toMap(
                                Dish::getCalories, // key
                                Function.identity(), // value 对象本身
                                (key1, key2) -> key2 // key重复进行覆盖
                        ));
        System.out.println(collect);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T i : list) {
            c.accept(i);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

}
