package com.uptoser.java.design_patterns.structural_patterns.facade;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
