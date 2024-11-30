package com.uptoser.java_learning.design_patterns.creational_patterns.builder;

public class Coke  extends ColdDrink{
    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}
