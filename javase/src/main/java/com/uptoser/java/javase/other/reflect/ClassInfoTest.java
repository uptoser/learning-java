package com.uptoser.java.javase.other.reflect;

import com.uptoser.java.javase.other.annotation.AnnotationMain;
import com.uptoser.java.javase.other.annotation.Application;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射查看类信息
 * 获取Class对象
 * 1.使用Class类的forName(String clazzName)静态方法
 * 2.调用某个类的class属性来获取该类对应的Class对象  例如，Person.class
 * 3.调用某个对象的getClass()方法
 * <p>
 * 下面4个方法用于获取Class对应类所包含的构造器。
 * ➢ Connstructor<T> getConstructor(Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的public构造器。
 * ➢ Constructor<?>[] getConstructors()：返回此Class对象对应类的所有public构造器。
 * ➢ Constructor<T> getDeclaredConstructor(Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的构造器，与构造器的访问权限无关。
 * ➢ Constructor<?>[] getDeclaredConstructors()：返回此Class对象对应类的所有构造器，与构造器的访问权限无关。
 * <p>
 * 下面4个方法用于获取Class对应类所包含的方法。
 * ➢ Method getMethod(String name, Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的public方法。
 * ➢ Method[] getMethods()：返回此Class对象所表示的类的所有public方法。
 * ➢ Method getDeclaredMethod(String name, Class<?>...parameterTypes)：返回此Class对象对应类的、带指定形参列表的方法，与方法的访问权限无关。
 * ➢ Method[] getDeclaredMethods()：返回此Class对象对应类的全部方法，与方法的访问权限无关。
 * <p>
 * 如下4个方法用于访问Class对应类所包含的成员变量。
 * ➢ Field getField(String name)：返回此Class对象对应类的、指定名称的public成员变量。
 * ➢ Field[] getFields()：返回此Class对象对应类的所有public成员变量。
 * ➢ Field getDeclaredField(String name)：返回此Class对象对应类的、指定名称的成员变量，与成员变量的访问权限无关。
 * ➢ Field[] getDeclaredFields()：返回此Class对象对应类的全部成员变量，与成员变量的访问权限无关。
 * <p>
 * 如下几个方法用于访问Class对应类上所包含的Annotation。
 * ➢ <A extends Annotation>A getAnnotation(Class<A> annotationClass)：尝试获取该Class对象对应类上存在的、指定类型的Annotation；如果该类型的注解不存在，则返回null。
 * ➢ <A extends Annotation>A getDeclaredAnnotation(Class<A> annotationClass)：这是Java 8新增的方法，该方法尝试获取直接修饰该Class对象对应类的、指定类型的Annotation；如果该类型的注解不存在，则返回null。
 * ➢ Annotation[] getAnnotations()：返回修饰该Class对象对应类上存在的所有Annotation。
 * ➢ Annotation[] getDeclaredAnnotations()：返回直接修饰该Class对应类的所有Annotation。
 * ➢ <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass)：该方法的功能与前面介绍的getAnnotation()方法基本相似。但由于Java 8增加了重复注解功能，因此需要使用该方法获取修饰该类的、指定类型的多个Annotation。
 * ➢ <A extends Annotation> A[] getDeclaredAnnotationsByType(Class<A> annotationClass)：该方法的功能与前面介绍的getDeclaredAnnotations ()方法基本相似。但由于Java 8增加了重复注解功能，因此需要使用该方法获取直接修饰该类的、指定类型的多个Annotation。
 * <p>
 * 如下方法用于访问该Class对象对应类包含的内部类。
 * ➢ Class<?>[] getDeclaredClasses()：返回该Class对象对应类里包含的全部内部类。
 * 如下方法用于访问该Class对象对应类所在的外部类。
 * ➢ Class<?> getDeclaringClass()：返回该Class对象对应类所在的外部类。
 * 如下方法用于访问该Class对象对应类所实现的接口。
 * ➢ Class<?>[] getInterfaces()：返回该Class对象对应类所实现的全部接口。
 * 如下几个方法用于访问该Class对象对应类所继承的父类。
 * ➢ Class<? super T > getSuperclass()：返回该Class对象对应类的超类的Class对象。
 * <p>
 * 如下方法用于获取Class对象对应类的修饰符、所在包、类名等基本信息。
 * ➢ int getModifiers()：返回此类或接口的所有修饰符。修饰符由public、protected、private、final、static、abstract等对应的常量组成，返回的整数应使用Modifier工具类的方法来解码，才可以获取真实的修饰符。
 * ➢ Package getPackage()：获取此类的包。
 * ➢ String getName()：以字符串形式返回此Class对象所表示的类的名称。
 * ➢ String getSimpleName()：以字符串形式返回此Class对象所表示的类的简称。
 * <p>
 * 除此之外，Class对象还可调用如下几个判断方法来判断该类是否为接口、枚举、注解类型等。
 * ➢ boolean isAnnotation()：返回此Class对象是否表示一个注解类型（由@interface定义）。
 * ➢ boolean isAnnotationPresent(Class<?extends Annotation> annotationClass)：判断此Class对象是否使用了Annotation修饰。
 * ➢ boolean isAnonymousClass()：返回此Class对象是否是一个匿名类。
 * ➢ boolean isArray()：返回此Class对象是否表示一个数组类。
 * ➢ boolean isEnum()：返回此Class对象是否表示一个枚举（由enum关键字定义）。
 * ➢ boolean isInterface()：返回此Class对象是否表示一个接口（使用interface定义）。
 * ➢ boolean isInstance(Object obj)：判断obj是否是此Class对象的实例，该方法可以完全代替instanceof操作符。
 * <p>
 * 上面的多个getMethod()方法和getConstructor()方法中，都需要传入多个类型为Class<?>的参数，用于获取指定的方法或指定的构造器。关于这个参数的作用，假设某个类内包含如下三个info方法签名。
 * ➢ public void info()
 * ➢ public void info(String str)
 * ➢ public void info(String str,Integer num)
 */

@SuppressWarnings(value="unchecked")
@Deprecated
@AnnotationMain(name = "你好世界", config = Application.class)
@AnnotationMain(name = "你好世界22", config = Application.class)
public class ClassInfoTest {

    private String name;

    private int age;



    // 为该类定义一个私有的构造器
    private ClassInfoTest() {
        System.out.println("执行无参的构造器");
    }

    // 定义一个有参数的构造器
    public ClassInfoTest(String name) {
        System.out.println("执行有参数的构造器:"+name);
    }

    // 定义一个无参数的info方法
    public void info() {
        System.out.println("执行无参数的info方法");
    }

    // 定义一个有参数的info方法
    public void info(String str) {
        System.out.println("执行有参数的info方法" + "，其str参数值：" + str);
    }

    // 定义一个测试用的内部类
    class Inner {
    }

    /**
     * 从class中获取信息
     */
    public static void getClassInfo() throws NoSuchMethodException, ClassNotFoundException {
        // 下面代码可以获取ClassTest对应的Class
        Class<ClassInfoTest> clazz = ClassInfoTest.class;
        // 获取该Class对象所对应类的全部构造器
        Constructor[] ctors = clazz.getDeclaredConstructors();
        System.out.println("ClassTest的全部构造器如下：");
        for (Constructor c : ctors)
        {
            System.out.println(c);
        }
        // 获取该Class对象所对应类的全部public构造器
        Constructor[] publicCtors = clazz.getConstructors();
        System.out.println("ClassTest的全部public构造器如下：");
        for (Constructor c : publicCtors)
        {
            System.out.println(c);
        }
        // 获取该Class对象所对应类的全部public方法
        Method[] mtds = clazz.getMethods();
        System.out.println("ClassTest的全部public方法如下：");
        for (Method md : mtds)
        {
            System.out.println(md);
        }
        // 获取该Class对象所对应类的指定方法
        System.out.println("ClassTest里带一个字符串参数的info方法为："
                + clazz.getMethod("info" , String.class));
        // 获取该Class对象所对应类的上的全部注释
        Annotation[] anns = clazz.getAnnotations();
        System.out.println("ClassTest的全部Annotation如下：");
        for (Annotation an : anns)
        {
            System.out.println(an);
        }
        System.out.println("该Class元素(可以获取父类)上的@SuppressWarnings注释为：" + clazz.getAnnotation(SuppressWarnings.class));

        // 获取该Class对象所对应类的全部内部类
        Class<?>[] inners = clazz.getDeclaredClasses();
        System.out.println("ClassTest的全部内部类如下：");
        for (Class c : inners)
        {
            System.out.println(c);
        }
        // 使用Class.forName方法加载ClassTest的Inner内部类
        Class inClazz = Class.forName("com.uptoser.java.javase.other.reflect.ClassInfoTest$Inner");
        // 通过getDeclaringClass()访问该类所在的外部类
        System.out.println("inClazz对应类的外部类为：" +
                inClazz.getDeclaringClass());
        System.out.println("ClassTest的包为：" + clazz.getPackage());
        System.out.println("ClassTest的父类为：" + clazz.getSuperclass());

    }

    public static void instanceAndInvoke() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = Class.forName("com.uptoser.java.javase.other.reflect.ClassInfoTest");
        /*
        如果不想利用默认构造器来创建Java对象，而想利用指定的构造器来创建Java对象，则需要利用Constructor对象，每个Constructor对应一个构造器。为了利用指定的构造器来创建Java对象，需要如下三个步骤。
        ① 获取该类的Class对象。
        ② 利用Class对象的getConstructor()方法来获取指定的构造器。
        ③ 调用Constructor的newInstance()方法来创建Java对象。
         */
        //获取带有一个字符串参数的构造器
        Constructor<?> constructor = clazz.getConstructor(String.class);
        ClassInfoTest target = (ClassInfoTest) constructor.newInstance("你好世界");
        //获取setter方法
        Method method = clazz.getMethod("info", String.class);
        method.invoke(target,"str");

        //通过getDeclaredField()方法表明可获取各种访问控制符的成员变量
        Field nameField = clazz.getDeclaredField("name");
        // 设置通过反射访问该Field时取消访问权限检查
        nameField.setAccessible(true);
        // 调用set方法为对象的name Field设置值
        nameField.set(target , "小宁");
        // 获取类名为age的属性
        Field ageField = clazz.getDeclaredField("age");
        System.out.println("ageField的类型为："+ageField.getType());
        // 设置通过反射访问该Field时取消访问权限检查
        ageField.setAccessible(true);
        // 调用setInt方法为p对象的age Field设置值
        ageField.setInt(target , 30);
        System.out.println(target);
    }

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        ClassInfoTest.getClassInfo();
//        ClassInfoTest.instanceAndInvoke();

    }
    @Override
    public String toString() {
        return "ClassInfoTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
