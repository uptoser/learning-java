package com.uptoser.design_patterns.creational_patterns.abstract_factory;
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}