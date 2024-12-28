package com.uptoser.java.javase.other.reflect;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

/**
 * Java 8在java.lang.reflect包下新增了一个Executable抽象基类，该对象代表可执行的类成员，该类派生了Constructor、Method两个子类。
 * Executable基类提供了大量方法来获取修饰该方法或构造器的注解信息；还提供了isVarArgs()方法用于判断该方法或构造器是否包含数量可变的形参，
 * 以及通过getModifiers()方法来获取该方法或构造器的修饰符。除此之外，Executable提供了如下两个方法来获取该方法或参数的形参个数及形参名。
 * ➢ int getParameterCount()：获取该构造器或方法的形参个数。
 * ➢ Parameter[] getParameters()：获取该构造器或方法的所有形参。
 * 上面第二个方法返回了一个Parameter[]数组，Parameter也是Java 8新增的API，每个Parameter对象代表方法或构造器的一个参数。
 * Parameter也提供了大量方法来获取声明该参数的泛型信息，还提供了如下常用方法来获取参数信息。
 * ➢ getModifiers()：获取修饰该形参的修饰符。
 * ➢ String getName()：获取形参名。
 * ➢ Type getParameterizedType()：获取带泛型的形参类型。
 * ➢ Class<?> getType()：获取形参类型。
 * ➢ boolean isNamePresent()：该方法返回该类的class文件中是否包含了方法的形参名信息。
 * ➢ boolean isVarArgs()：该方法用于判断该参数是否为个数可变的形参。
 *
 * 使用javac命令编译Java源文件时，默认生成的class文件并不包含方法的形参名信息，
 * 因此调用isNamePresent()方法将会返回false，调用getName()方法也不能得到该参数的形参名。
 * 如果希望javac命令编译Java源文件时可以保留形参信息，则需要为该命令指定-parameters选项
 */
public class ParameterizedTypeTest {

    //获取参数信息
    public void methodParameter() throws NoSuchMethodException {
        Class<Test> clazz = Test.class;
        //获取String类型带两个参数的replace方法
        Method replace = clazz.getMethod("replace", String.class, List.class);
        //获取指定方法的参数的个数
        System.out.println("replace方法参数的个数是:"+replace.getParameterCount());
        //获取replace方法的所有参数信息
        Parameter[] parameters = replace.getParameters();
        int i = 1;
        for (Parameter parameter : parameters) {
            if(parameter.isNamePresent()){
                System.out.printf("-----第%d个参数信息-----\n",i);
                System.out.println("参数名："+parameter.getName());
                System.out.println("型参类型："+parameter.getName());
                System.out.println("泛型类型："+parameter.getParameterizedType());
            }

        }
    }

    /**
     * 使用反射来获取泛型信息
     *
     * ParameterizedType类提供了如下两个方法。
     * ➢ getRawType()：返回没有泛型信息的原始类型。
     * ➢ getActualTypeArguments()：返回泛型参数的类型。
     */
    public void genericTypeTest() throws NoSuchFieldException {
        Class<Test> clazz = Test.class;
        Field mapField = clazz.getField("map");
        Type genericType = mapField.getGenericType();
        //如果是genericType类型是ParameterizedType对象
        if(genericType instanceof ParameterizedType){
            //强转
            ParameterizedType parameterType = (ParameterizedType)genericType;
            //获取原始类型
            Type rawType = parameterType.getRawType();
            System.out.println("原始类型为："+rawType);
            //取得泛型类型的泛型参数
            Type[] actualTypeArguments = parameterType.getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                System.out.printf("第%d个泛型类型是:%s\n",i,actualTypeArguments[i]);
            }
        }else{
            System.out.println("获取泛型类型出错");
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        ParameterizedTypeTest p = new ParameterizedTypeTest();
        //-parameters选项用于控制javac命令保留方法形参名信息
        p.methodParameter();
        p.genericTypeTest();
    }
}
class Test{
    public Map<String,Object> map;
    public void replace(String str, List<String> list){}
}
