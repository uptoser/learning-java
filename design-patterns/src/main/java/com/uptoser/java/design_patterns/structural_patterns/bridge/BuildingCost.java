package com.uptoser.java.design_patterns.structural_patterns.bridge;

/**
 * 细化抽象(Refined Abstraction)细化抽象是抽象角色的一个子类，
 * 该子类在重写（覆盖）抽象角色中的抽象方法时，在给出一些必要的操作后，
 * 将委托所维护 Implementor类型对象调用相应的方法。
 */
public class BuildingCost extends ArchitectureCost {
    BuildingCost(BuildingDesign design, double unitPrice) {
        this.design = design;
        this.unitPrice = unitPrice;
    }

    public double giveCost() {
        double area = design.computerArea();
        return area * unitPrice;
    }
}   
