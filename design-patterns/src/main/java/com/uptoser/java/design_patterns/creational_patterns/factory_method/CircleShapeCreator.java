package com.uptoser.java.design_patterns.creational_patterns.factory_method;

public class CircleShapeCreator extends ShapeFactory{
    @Override
    public Shape getShape() {
        return new Circle();
    }
}
