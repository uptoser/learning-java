package com.uptoser.java.javase.other.annotation;

import java.lang.annotation.*;

/**
 * Java提供的5个基本注解
 * ➢ @Override               限定重写父类方法
 * ➢ @Deprecated             表示某个程序元素（类、方法等）已过时
 * ➢ @SuppressWarnings       抑制编译器警告
 * ➢ @SafeVarargs            堆污染”警告
 * ➢ @FunctionalInterface    函数式接口
 *
 * JDK除在java.lang下提供了5个基本的注解之外，还在java.lang.annotation包下提供了6个Meta注解（元注解）
 * ➢ @Retention只能用于修饰注解定义,使用时必须为该value成员变量指定值
 * RetentionPolicy.CLASS：编译器将把注解记录在class文件中。当运行Java程序时，JVM不可获取注解信息。这是默认值。
 * RetentionPolicy.RUNTIME：编译器将把注解记录在class文件中。当运行Java程序时，JVM也可获取注解信息，程序可以通过反射获取该注解信息。
 * RetentionPolicy.SOURCE：注解只保留在源代码中，编译器直接丢弃这种注解。
 * ➢ @Target只能修饰注解定义,它用于指定被修饰的注解能用于修饰哪些程序单元。注解也包含一个名为value的成员变量
 * ElementType.ANNOTATION_TYPE：指定该策略的注解只能修饰注解。
 * ElementType.CONSTRUCTOR：指定该策略的注解只能修饰构造器。
 * ElementType.FIELD：指定该策略的注解只能修饰成员变量。
 * ElementType.LOCAL_VARIABLE：指定该策略的注解只能修饰局部变量。
 * ElementType.METHOD：指定该策略的注解只能修饰方法定义。
 * ElementType.PACKAGE：指定该策略的注解只能修饰包定义。
 * ElementType.PARAMETER：指定该策略的注解可以修饰参数。
 * ElementType.TYPE：指定该策略的注解可以修饰类、接口（包括注解类型）或枚举定义。
 * ➢ 类型注解（Type Annotation） Java 8为ElementType枚举增加了TYPE_PARAMETER、TYPE_USE两个枚举值
 * ElementType.TYPE_PARAMETER：可以修饰类型参数。//比如在泛型中就可以使用
 * ElementType.TYPE_USE：可用于修饰在任何地方出现的类型
 * ➢ @Documented用于指定被该元注解修饰的注解类将被javadoc工具提取成文档
 * ➢ @Inherited指定被它修饰的注解将具有继承性
 * ➢ @Repeatable 重复注解
 *
 *
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE_USE})
@Documented//用于指定被该元注解修饰的注解类将被javadoc工具提取成文档。文档中会出现注解
@Inherited//指定被它修饰的注解将具有继承性。子类会自动加上自定义的注解
@Repeatable(AnnotationMains.class) //重复注解
public @interface AnnotationMain {
    /*
     * 根据注解是否可以包含成员变量，可以把注解分为如下两类。
     * ➢ 标记注解：没有定义成员变量的注解类型被称为标记。这种注解仅利用自身的存在与否来提供信息，如@Override、@Test等注解。
     * ➢ 元数据注解：包含成员变量的注解，因为它们可以接受更多的元数据，所以也被称为元数据注解。
     */
    String name();
    int age() default 18;//使用default指定了初始值
    Class<?> config();
}
