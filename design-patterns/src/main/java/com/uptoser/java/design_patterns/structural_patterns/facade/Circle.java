package com.uptoser.java.design_patterns.structural_patterns.facade;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
