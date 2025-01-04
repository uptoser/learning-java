package com.uptoser.java.design_patterns.structural_patterns.adapter;

//电视机使用两相插座
public class TV implements ThreeElectricOutlet {
    String name;

    TV() {
        name = "电视机";
    }

    TV(String s) {
        name = s;
    }
    @Override
    public void connectElectricCurrent() {
        turnOn();
    }

    public void turnOn() {
        System.out.println(name + "开始播放节目。");
    }
}
