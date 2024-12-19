package com.uptoser.java.javase.other.reflect;

/**
 * 通过反射查看类信息 TODO
 * 获取Class对象
 * 1.使用Class类的forName(String clazzName)静态方法
 * 2.调用某个类的class属性来获取该类对应的Class对象  例如，Person.class
 * 3.调用某个对象的getClass()方法
 *
 * 下面4个方法用于获取Class对应类所包含的构造器。
 * ➢ Connstructor<T> getConstructor(Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的public构造器。
 * ➢ Constructor<?>[] getConstructors()：返回此Class对象对应类的所有public构造器。
 * ➢ Constructor<T> getDeclaredConstructor(Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的构造器，与构造器的访问权限无关。
 * ➢ Constructor<?>[] getDeclaredConstructors()：返回此Class对象对应类的所有构造器，与构造器的访问权限无关。
 *
 * 下面4个方法用于获取Class对应类所包含的方法。
 * ➢ Method getMethod(String name, Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的public方法。
 * ➢ Method[] getMethods()：返回此Class对象所表示的类的所有public方法。
 * ➢ Method getDeclaredMethod(String name, Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的方法，与方法的访问权限无关。
 * ➢ Method[] getDeclaredMethods()：返回此Class对象对应类的全部方法，与方法的访问权限无关。
 *
 * 如下4个方法用于访问Class对应类所包含的成员变量。
 * ➢ Field getField(String name)：返回此Class对象对应类的、指定名称的public成员变量。
 * ➢ Field[] getFields()：返回此Class对象对应类的所有public成员变量。
 * ➢ Field getDeclaredField(String name)：返回此Class对象对应类的、指定名称的成员变量，与成员变量的访问权限无关。
 * ➢ Field[] getDeclaredFields()：返回此Class对象对应类的全部成员变量，与成员变量的访问权限无关。
 *
 * 如下几个方法用于访问Class对应类上所包含的Annotation。
 * ➢ <A extends Annotation>A getAnnotation(Class<A> annotationClass)：尝试获取该Class对象对应类上存在的、指定类型的Annotation；如果该类型的注解不存在，则返回null。
 * ➢ <A extends Annotation>A getDeclaredAnnotation(Class<A> annotationClass)：这是Java 8新增的方法，该方法尝试获取直接修饰该Class对象对应类的、指定类型的Annotation；如果该类型的注解不存在，则返回null。
 * ➢ Annotation[] getAnnotations()：返回修饰该Class对象对应类上存在的所有Annotation。
 * ➢ Annotation[] getDeclaredAnnotations()：返回直接修饰该Class对应类的所有Annotation。
 * ➢ <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass)：该方法的功能与前面介绍的getAnnotation()方法基本相似。但由于Java 8增加了重复注解功能，因此需要使用该方法获取修饰该类的、指定类型的多个Annotation。
 * ➢ <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass)：该方法的功能与前面介绍的getDeclaredAnnotations ()方法基本相似。但由于Java 8增加了重复注解功能，因此需要使用该方法获取直接修饰该类的、指定类型的多个Annotation。
 *
 * 如下方法用于访问该Class对象对应类包含的内部类。
 * ➢ Class<?>[] getDeclaredClasses()：返回该Class对象对应类里包含的全部内部类。
 * 如下方法用于访问该Class对象对应类所在的外部类。
 * ➢ Class<?> getDeclaringClass()：返回该Class对象对应类所在的外部类。
 * 如下方法用于访问该Class对象对应类所实现的接口。
 * ➢ Class<?>[] getInterfaces()：返回该Class对象对应类所实现的全部接口。
 * 如下几个方法用于访问该Class对象对应类所继承的父类。
 * ➢ Class<? super T > getSuperclass()：返回该Class对象对应类的超类的Class对象。
 *
 * 如下方法用于获取Class对象对应类的修饰符、所在包、类名等基本信息。
 * ➢ int getModifiers()：返回此类或接口的所有修饰符。修饰符由public、protected、private、final、static、abstract等对应的常量组成，返回的整数应使用Modifier工具类的方法来解码，才可以获取真实的修饰符。
 * ➢ Package getPackage()：获取此类的包。
 * ➢ String getName()：以字符串形式返回此Class对象所表示的类的名称。
 * ➢ String getSimpleName()：以字符串形式返回此Class对象所表示的类的简称。
 *
 * 除此之外，Class对象还可调用如下几个判断方法来判断该类是否为接口、枚举、注解类型等。
 * ➢ boolean isAnnotation()：返回此Class对象是否表示一个注解类型（由@interface定义）。
 * ➢ boolean isAnnotationPresent(Class<?extends Annotation> annotationClass)：判断此Class对象是否使用了Annotation修饰。
 * ➢ boolean isAnonymousClass()：返回此Class对象是否是一个匿名类。
 * ➢ boolean isArray()：返回此Class对象是否表示一个数组类。
 * ➢ boolean isEnum()：返回此Class对象是否表示一个枚举（由enum关键字定义）。
 * ➢ boolean isInterface()：返回此Class对象是否表示一个接口（使用interface定义）。
 * ➢ boolean isInstance(Object obj)：判断obj是否是此Class对象的实例，该方法可以完全代替instanceof操作符。
 *
 * 上面的多个getMethod()方法和getConstructor()方法中，都需要传入多个类型为Class<?>的参数，用于获取指定的方法或指定的构造器。关于这个参数的作用，假设某个类内包含如下三个info方法签名。
 * ➢ public void info()
 * ➢ public void info(String str)
 * ➢ public void info(String str,Integer num)
 *
 */
public class ClassInfoTest {
    public static void main(String[] args) {
        /*
         */

        //从Class中获取信息


    }
}
