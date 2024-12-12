package com.uptoser.java.javase.classobject;

import org.omg.CORBA.Object;

/**
 * 多态
 */
public class Polymorphism {
    {
        //初始化块
        a = 6;
        System.out.println("初始化块");
    }

    static {
        //类的初始化块
        System.out.println("类的初始化块");
    }

    int a = 9;

    public Polymorphism(){
        System.out.println("构造器a="+a);
    }

    public static void main(String[] args) {
        new Polymorphism();
        Inheritance obj = new Inheritance().new ChildrenObject();
        obj.say();
        System.out.println(obj instanceof Object);//Object类是所有类、接口的父类，因此可以执行。返回false
        System.out.println(obj instanceof Inheritance.ChildrenObject);//返回true
        System.out.println(obj instanceof Inheritance);//返回true
//        System.out.println(obj instanceof Math);//既不是Math类型，也不是Math类型的父类，所以这行代码编译就会出错
    }


}


