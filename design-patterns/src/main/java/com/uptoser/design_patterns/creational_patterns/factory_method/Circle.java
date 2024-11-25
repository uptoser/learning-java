package com.uptoser.design_patterns.creational_patterns.factory_method;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
