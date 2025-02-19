package com.uptoser.java.design_patterns.behavioral_patterns.state.group2;

public class BulletStateNull implements State {
    Gun gun;

    BulletStateNull(Gun gun) {
        this.gun = gun;
    }

    public void fire() {
        System.out.print("不能射出子弹");
        System.out.println("(目前是" + showStateMess() + ")");
    }

    public void loadBullet() {
        System.out.print("装弹");
        gun.setState(gun.getBulletStateThree());
        System.out.println("(进入" + gun.getBulletStateThree().showStateMess() + ")");
    }

    public String showStateMess() {
        return "无子弹状态";
    }
}  
