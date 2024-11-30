package com.uptoser.java.design_patterns.structural_patterns.facade;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
