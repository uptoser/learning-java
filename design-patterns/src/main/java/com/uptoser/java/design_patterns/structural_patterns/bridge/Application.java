package com.uptoser.java.design_patterns.structural_patterns.bridge;

/**
 * 桥接模式
 * 用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦
 * <p>
 * Bridge Pattern(Another Name: Handle-Body)
 * Decouple an abstraction from its implementation so that the two can vary independently.
 *
 * 桥接模式分离实现与抽象，使抽象和实现可以独立的扩展。当修改实现的代码时，不影响抽象的代码，反之也一样。
 */
public class Application {

    public static void main(String[] args) {
        double width = 63, height = 30;
        int floorNumber = 8;
        double unitPrice = 6867.38;
        BuildingDesign design = new HouseDesign(width, height, floorNumber);
        System.out.println("宽" + width + "米，高" + height + "米，层数为" + floorNumber);
        ArchitectureCost cost = new BuildingCost(design, unitPrice);
        double price = cost.giveCost();
        System.out.printf("每平米造价：" + unitPrice + "元的商业楼的建设成本：%.2f元\n", price);
        width = 52;
        height = 28;
        floorNumber = 6;
        unitPrice = 2687.88;
        design = new HouseDesign(width, height, floorNumber);
        System.out.println("宽" + width + "米，高" + height + "米，层数为" + floorNumber);
        cost = new BuildingCost(design, unitPrice);
        price = cost.giveCost();
        System.out.printf("每平米造价：" + unitPrice + "元的住宅楼的建设成本：%.2f元\n", price);
    }

}
