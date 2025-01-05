package com.uptoser.java.design_patterns.behavioral_patterns.state.group2;

/**
 * 状态切换
 * 环境（ context ）实例在某种状态下执行一个方法后，可能导致该实例的状态发生变化。
 * 程序通过使用状态模式可方便地将环境（ context ）实例从一个状态切换为另一个状态。
 * 当一个环境（ context ）实例有确定的若干个状态时，可以由环境（ context ）实例本身负责状态的切换，
 * 该环境（ context ）实例可以含有所有状态的引用，并提供设置改变状态的方法，比如setState(State state) 方法。
 */
public class Application {
    public static void main(String[] args) {
        Gun gun=new Gun();
        gun.fire();
        gun.fire();
        gun.fire();
        gun.fire();
        gun.loadBullet();
        gun.fire();
    }
}
