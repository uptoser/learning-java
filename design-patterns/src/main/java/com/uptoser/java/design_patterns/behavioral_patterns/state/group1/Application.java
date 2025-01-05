package com.uptoser.java.design_patterns.behavioral_patterns.state.group1;

/**
 * 在状态模式（State Pattern）中，类的行为是基于它的状态改变的。这种类型的设计模式属于行为型模式。
 * 允许一个对象在其内部状态改变时改变它的行为，对象看起来似乎修改了它的类。
 *
 * State Pattern(Another Name: Objects for States)
 * Allow an object to alter its behavior when its internal state changes. The object
 * will appear to change its class.
 *
 * 应用实例：
 * 打篮球的时候运动员可以有正常状态、不正常状态和超常状态。
 */
public class Application {
    public static void main(String[] args) {
        Context context = new Context();
        StartState startState = new StartState();
        context.setState(startState);
        context.request();
        StopState stopState = new StopState();
        context.setState(stopState);
        context.request();
    }
}
