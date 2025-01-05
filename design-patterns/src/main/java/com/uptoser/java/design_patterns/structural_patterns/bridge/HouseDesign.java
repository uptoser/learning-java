package com.uptoser.java.design_patterns.structural_patterns.bridge;

/**
 * 具体实现者 (Concrete Implementor) ：具体实现者是实现（扩展） Implementor 接口（抽象类）的类。
 */
public class HouseDesign implements BuildingDesign {
    double width, length;
    int floorNumber;

    HouseDesign(double width, double length, int floorNumber) {
        this.width = width;
        this.length = length;
        this.floorNumber = floorNumber;
    }

    public double computerArea() {
        return width * length * floorNumber;
    }
}