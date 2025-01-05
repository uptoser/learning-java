package com.uptoser.java.design_patterns.behavioral_patterns.state.group2;

public class BulletStateThree implements State {
    Gun gun;

    BulletStateThree(Gun gun) {
        this.gun = gun;
    }

    public void fire() {
        System.out.print("射出一颗子弹");
        gun.setState(gun.getBulletStateTwo());
        System.out.println("(进入" + gun.getBulletStateTwo().showStateMess() + ")");
    }

    public void loadBullet() {
        System.out.println("无法装弹");
    }

    public String showStateMess() {
        return "3颗子弹状态";
    }
}

