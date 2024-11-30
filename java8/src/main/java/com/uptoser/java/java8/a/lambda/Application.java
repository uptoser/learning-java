package com.uptoser.java.java8.a.lambda;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//基础与Lambda表达式
public class Application {

    private Application() {
    }

    public static void main(String[] args) {
        //构造器
        Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Supplier<Apple> c1 = Apple::new;
        Function<Integer, Apple> c2 = Apple::new;
        //Predicate Test
        Predicate<String> predicate = "1"::equals;
        List<String> filter = filter(Arrays.asList("1", "22", "333"), predicate);
        //Consumer Test
        Consumer consumer = System.out::println;
        forEach(filter, System.out::println);
        //Function Test
        Function<String, Integer> function = String::length;
        List<Integer> map = map(Arrays.asList("1", "22", "333"), function);
        forEach(map, System.out::println);

        //比较器复合
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        c.reversed();//按重量递减排序
        //按重量递减排序 --> 进一步按国家排序
        Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry);

        //谓词复合
        Predicate<Apple> redApple = apple -> Color.Red == apple.getColor();
        Predicate<Apple> notRedApple = redApple.negate();//非
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);//与
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150)
                .or(apple -> Color.Green == apple.getColor()); //链接三个谓词来

        //函数复合
        //数学上会写作g(f(x))或(g o f)(x)
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        //数学上会写作f(g(x))或(f o g)(x)
//        Function<Integer, Integer> f = x -> x + 1;
//        Function<Integer, Integer> g = x -> x * 2;
//        Function<Integer, Integer> h = f.compose(g);


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

//@FunctionalInterface
//interface Consumer<T> {
//    void accept(T t);
//}
//
//@FunctionalInterface
//interface Predicate<T> {
//    boolean test(T t);
//}
//
//@FunctionalInterface
//interface Function<T, R> {
//    R apply(T t);
//}
