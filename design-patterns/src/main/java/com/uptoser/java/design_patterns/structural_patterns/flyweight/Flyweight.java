package com.uptoser.java.design_patterns.structural_patterns.flyweight;

/**
 * 享元接口（ Flyweight)
 * 是一个接口，该接口定义了享元对外公开其内部数据的方法，以及享元接收外部数据的方法。
 */
public interface Flyweight {
    public double getHeight();
    public double getWidth();
    public double getLength();
    public void printMess(String mess);
}
