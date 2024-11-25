package com.uptoser.design_patterns.creational_patterns.abstract_factory;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
