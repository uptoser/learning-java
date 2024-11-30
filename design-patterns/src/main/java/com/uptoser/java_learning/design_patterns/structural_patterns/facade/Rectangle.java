package com.uptoser.java_learning.design_patterns.structural_patterns.facade;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
