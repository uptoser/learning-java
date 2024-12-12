package com.uptoser.java.javase.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//基础与Lambda表达式
public class Application {

    private Application() {
    }

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
     *
     * @param args
     */
    public static void main(String[] args) {
        //构造器
        Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Supplier<Apple> c1 = Apple::new;
        Function<Integer, Apple> c2 = Apple::new;
        //Predicate(谓词)  布尔返回类型
        Predicate<String> predicate = "1"::equals;
        List<String> filter = filter(Arrays.asList("1", "22", "333"), predicate);
        //Consumer(消费者) 没有返回类型
        Consumer consumer = System.out::println;
        forEach(filter, System.out::println);
        //Function<T, R> 接受泛型T对象，返回泛型R对象
        Function<String, Integer> function = String::length;
        List<Integer> map = map(Arrays.asList("1", "22", "333"), function);
        forEach(map, System.out::println);

        //比较器复合
        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        Comparator<Apple> reversed = c.reversed();//按重量递减排序
        //按重量递减排序 --> 两个苹果一样重时，进一步按国家排序
        Comparator<Apple> appleComparator = reversed.thenComparing(Apple::getCountry);

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
