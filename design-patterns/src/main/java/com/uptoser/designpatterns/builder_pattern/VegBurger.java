package com.uptoser.designpatterns.builder_pattern;

/**
 * 素食汉堡
 */
public class VegBurger extends Burger{
    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
