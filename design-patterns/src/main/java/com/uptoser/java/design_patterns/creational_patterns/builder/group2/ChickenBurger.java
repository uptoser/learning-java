package com.uptoser.java.design_patterns.creational_patterns.builder.group2;

/**
 * 鸡肉汉堡
 */
public class ChickenBurger extends Burger {
    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}
