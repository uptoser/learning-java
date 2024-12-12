package com.uptoser.java.javase.classobject;

import java.util.Arrays;

/**
 * 接口里定义的方法只能是抽象方法、类方法、默认方法或私有方法，因此如果不是定义默认方法、类方法或私有方法，系统将自动为普通方法增加abstract修饰符；
 * 定义接口里的普通方法时不管是否使用public abstract修饰符，接口里的普通方法总是使用public abstract来修饰。
 *
 * 接口的继承和类继承不一样，接口完全支持多继承
 */
//Java 8专门为函数式接口提供了@FunctionalInterface注解，该注解通常放在接口定义前面，该注解对程序功能没有任何作用，它用于告诉编译器执行更严格检查—检查该接口必须是函数式接口，否则编译器就会报错
@FunctionalInterface
public interface InterfaceMain {
    //接口里的成员变量默认是使用public static final修饰的
    int MAX = 69;//接口里定义的变量只能是常量
    //接口里定义的方法只能是public抽象方法
    void say();
    //接口中定义的默认方法需要用default修饰
    default void print(String... msg){
        Arrays.stream(msg).forEach(System.out::println);
        say();
    }
    //接口中定义的默认方法需要用default修饰
    default void test(String... msg){
        Arrays.stream(msg).forEach(System.out::println);
    }
    //接口中的类方法 static
    static String staticTest(){
        return "接口中的类方法";
    }
    //JDK9中增加了 接口中的私有方法
//    private void foo(){
//        System.out.println("接口中私有方法");
//    }

    public static void main(String[] args) {
        //Lambda表达式
        InterfaceMain i = ()->System.out.println("hello");
        //局部内部类
        class J {J(InterfaceMain i){i.say();}}
        new J(i);
        //匿名内部类
        new InterfaceMain() {
            @Override
            public void say() {
                //System.out.println("hello")
            }
        }.print("3","2","1");

        System.out.println(Apple.A);
    }

    /**
     * 静态内部类是外部类的一个静态成员，因此外部类的所有方法、所有初始化块中可以使用静态内部类来定义变量、创建对象等。
     * 外部类依然不能直接访问静态内部类的成员，但可以使用静态内部类的类名作为调用者来访问静态内部类的类成员，
     * 也可以使用静态内部类对象作为调用者来访问静态内部类的实例成员
     */
    static class Apple{ public final static int A = 0;}
    class Banana{ public final static int B = 0;}

}
