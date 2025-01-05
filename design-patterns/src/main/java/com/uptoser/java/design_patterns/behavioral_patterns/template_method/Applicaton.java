package com.uptoser.java.design_patterns.behavioral_patterns.template_method;

/**
 * 模板方法模式
 * 定义一个操作中算法的骨架，而将一些步骤延迟到子类中。模板方法使子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
 *
 * Template Method Pattern
 * Define the skeleton of an algorithm in an operation, deferring some steps to
 * subclasses. Template Method lets subclasses redefine certain steps of an algorithm
 * without changing the algorithm's structure.
 *
 */
public class Applicaton {
    public static void main(String[] args) {
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }
}
