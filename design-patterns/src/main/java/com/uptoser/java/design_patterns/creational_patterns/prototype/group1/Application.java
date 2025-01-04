package com.uptoser.java.design_patterns.creational_patterns.prototype.group1;


import java.io.IOException;

/**
 * 原型模式：用原型实例指定创建对象的种类，并且通过复制这些原型创建新的对象。
 * <p>
 * Prototype Pattern
 * Specify the kinds of objects to create using a prototypical instance, and create new
 * objects by copying this prototype.
 * <p>
 * JVM 要求调用 clone()方法的对象必须实现Cloneable接口
 */
public class Application {

    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Books book1 = new Books("Book1");
        Student a = new Student(book1, "小A", 10);
        Student clonea = a.clone();
        System.out.println("clone->Student对象是否是同一个：" + (a == clonea));
        System.out.println("clone->Books对象是否是同一个：" + (a.getBook() == clonea.getBook()));
        //深度克隆
        clonea = a.clone2();
        System.out.println("clone2->Student对象是否是同一个：" + (a == clonea));
        System.out.println("clone2->Books对象是否是同一个：" + (a.getBook() == clonea.getBook()));
        //通过序列化复制对象
        clonea = a.clone3();
        System.out.println("clone3->Student对象是否是同一个：" + (a == clonea));
        System.out.println("clone3->Books对象是否是同一个：" + (a.getBook() == clonea.getBook()));
    }

}

