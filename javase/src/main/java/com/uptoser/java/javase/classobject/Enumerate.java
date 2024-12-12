package com.uptoser.java.javase.classobject;

import java.util.Arrays;

/**
 * 枚举类
 *
 * 所有的枚举类都继承了java.lang.Enum类，所以枚举类可以直接使用java.lang.Enum类中所包含的方法
 * ➢ int compareTo(E o)：该方法用于与指定枚举对象比较顺序，同一个枚举实例只能与相同类型的枚举实例进行比较。
 * 如果该枚举对象位于指定枚举对象之后，则返回正整数；如果该枚举对象位于指定枚举对象之前，则返回负整数，否则返回零。
 * ➢ String name()：返回此枚举实例的名称，这个名称就是定义枚举类时列出的所有枚举值之一。
 * 与此方法相比，大多数程序员应该优先考虑使用toString()方法，因为toString()方法返回更加用户友好的名称。
 * ➢ int ordinal()：返回枚举值在枚举类中的索引值（就是枚举值在枚举声明中的位置，第一个枚举值的索引值为零）。
 * ➢ String toString()：返回枚举常量的名称，与name方法相似，但toString()方法更常用。
 * ➢ public static<T extends Enum<T>> T valueOf(Class<T> enumType，String name)：这是一个静态方法，
 * 用于返回指定枚举类中指定名称的枚举值。名称必须与在该枚举类中声明枚举值时所用的标识符完全匹配，不允许使用额外的空白字符。
 */
/*
非抽象的枚举类才默认使用final修饰。对于一个抽象的枚举类而言——只要它包含了抽象方法，
它就是抽象枚举类，系统会默认使用abstract修饰，而不是使用final修饰
 */
public enum Enumerate implements InterfaceMain{
    //枚举类的所有实例必须在枚举类的第一行显式列出，否则这个枚举类永远都不能产生实例
    GENDER("性别")
    ,
    SEASON("季节"){
        @Override
        public void say() {
            super.say();
        }
    };
    //定义了一个名为name的实例变量
    private String name;
    //枚举的构造器只能用private修饰
    private Enumerate(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void say() {
        System.out.println();
    }

    public static void printNames() {
        Enumerate[] values = values();
        Arrays.stream(values).forEach(v->System.out.println(v.getName()));
    }

    public static void main(String[] args) {
        Enumerate gender = Enumerate.valueOf("GENDER");
        System.out.println(gender);
        Enumerate.printNames();
    }
}
