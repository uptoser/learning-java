package com.uptoser.java.design_patterns.creational_patterns.prototype.group2;

public class Square extends Shape{
    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
