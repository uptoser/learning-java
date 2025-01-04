package com.uptoser.java.design_patterns.creational_patterns.builder.group1;
/**
 * 生成器模式/建造者模式
 * 将一个复杂对象的构建与它的表示分离，使同样的构建过程可以创建不同的表示
 * 使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * <p>
 * Builder Pattern
 * Separate the construction of a complex object from its representation so that the
 * same construction process can create different representations.
 */

import javax.swing.*;

public class Application {
    public static void main(String args[]) {
        Builder builder = new ConcreteBuilderOne();
        Director director = new Director(builder);
        JPanel panel = director.constructProduct();
        JFrame frameOne = new JFrame();
        frameOne.add(panel);
        frameOne.setBounds(12, 12, 200, 120);
        frameOne.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameOne.setVisible(true);
        builder = new ConcreteBuilderTwo();
        director = new Director(builder);
        panel = director.constructProduct();
        JFrame frameTwo = new JFrame();
        frameTwo.add(panel);
        frameTwo.setBounds(212, 12, 200, 120);
        frameTwo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameTwo.setVisible(true);
    }
}
