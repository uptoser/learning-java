package com.uptoser.java.design_patterns.behavioral_patterns.state.group3;

/**
 * 共享状态
 * 环境（ context ）的多个实例可以共享同一个状态，
 * 比如我们经常见到的客运列车中的车厢都有两种状态，即“运动状态"和“静止状态"，
 * 那么一列客运列车的所有车厢就要共享“运动状态"和“静止状态"
 * 在状态模式中，要使环境（ context ）的多个实例共享一个或多个状态，
 * 需要将这些状态声明为环境（ context ）的静态成员，
 * 另外，一定要保证共享的状态没有自己的实例变量，否则是无法共享的。
 */
public class Application {
    public static void main(String[] args) {
        Vehicle carOne=new Vehicle("卧铺车厢");
        Vehicle carTwo=new Vehicle("普通车厢");
        carOne.startUp();
        carTwo.startUp();
        carTwo.stop();
        carOne.stop();
    }
}
