package com.uptoser.java.design_patterns.structural_patterns.adapter;

//洗衣机使用三相插座
public class Washer implements TwoElectricOutlet {
    String name;

    Washer() {
        name = "洗衣机";
    }

    Washer(String s) {
        name = s;
    }
    @Override
    public void connectElectricCurrent() {
        turnOn();
    }

    public void turnOn() {
        System.out.println(name + "开始洗衣物。");
    }
}
