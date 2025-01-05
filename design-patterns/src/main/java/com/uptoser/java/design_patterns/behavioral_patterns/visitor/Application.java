package com.uptoser.java.design_patterns.behavioral_patterns.visitor;

/**
 * 访问者模式（Visitor Pattern）
 * 表示一个作用于某对象结构中的各个元素的操作。它可以在不改变各个元素的类的前提下定义作用于这些元素的新操作。
 *
 * Visitor Pattern
 * Represent an operation to be performed on the elements of an object structure.
 * Visitor lets you define a new operation without changing the classes of the elements on
 * which it operates.
 */
public class Application {
    public static void main(String[] args) {
        ComputerPartDisplayVisitor visitor = new ComputerPartDisplayVisitor();
        Computer computer = new Computer();
        computer.accept(visitor);
        System.out.println();
        //双重分派 访问者 visitor 可以调用 visit方法访问当前 element 对象。
        Keyboard keyboard = new Keyboard();
        visitor.visit(keyboard);
    }
}
