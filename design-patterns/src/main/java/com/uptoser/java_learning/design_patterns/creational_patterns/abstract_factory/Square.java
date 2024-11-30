package com.uptoser.java_learning.design_patterns.creational_patterns.abstract_factory;

public class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
