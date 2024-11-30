package com.uptoser.java.design_patterns.creational_patterns.abstract_factory;

public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}