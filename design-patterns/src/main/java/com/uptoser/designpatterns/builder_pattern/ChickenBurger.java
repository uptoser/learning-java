package com.uptoser.designpatterns.builder_pattern;

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
