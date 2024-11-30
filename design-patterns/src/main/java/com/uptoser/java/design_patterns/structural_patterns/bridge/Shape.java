package com.uptoser.java.design_patterns.structural_patterns.bridge;

public abstract  class Shape {
    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
