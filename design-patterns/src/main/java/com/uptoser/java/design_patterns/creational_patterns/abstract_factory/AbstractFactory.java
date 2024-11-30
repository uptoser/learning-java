package com.uptoser.java.design_patterns.creational_patterns.abstract_factory;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
