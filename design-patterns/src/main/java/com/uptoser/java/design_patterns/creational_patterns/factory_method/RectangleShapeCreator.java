package com.uptoser.java.design_patterns.creational_patterns.factory_method;

public class RectangleShapeCreator extends ShapeFactory{
    @Override
    public Shape getShape() {
        return new Rectangle();
    }
}
