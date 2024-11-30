package com.uptoser.java_learning.design_patterns.creational_patterns.abstract_factory;

public class Green implements Color{
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
