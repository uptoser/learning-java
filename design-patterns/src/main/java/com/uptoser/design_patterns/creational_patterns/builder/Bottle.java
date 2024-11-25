package com.uptoser.design_patterns.creational_patterns.builder;

public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
