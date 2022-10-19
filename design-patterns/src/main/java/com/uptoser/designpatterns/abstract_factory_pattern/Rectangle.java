package com.uptoser.designpatterns.abstract_factory_pattern;

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
