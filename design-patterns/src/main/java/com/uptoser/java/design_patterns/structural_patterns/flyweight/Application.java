package com.uptoser.java.design_patterns.structural_patterns.flyweight;

/**
 * 享元模式（Flyweight Pattern）
 * 运用共享技术有效地支持大量细粒度的对象。
 * 主要用于减少创建对象的数量，以减少内存占用和提高性能。
 * <p>
 * Flyweight Pattern
 * Use sharing to support large numbers of fine-grained objects efficiently.
 */
public class Application {

    public static void main(String[] args) {
        FlyweightFactory factory = FlyweightFactory.getFactory();
        double width = 1.82, height = 1.47, length = 5.12;
        String key = "" + width + "#" + height + "#" + length;
        Flyweight flyweight = factory.getFlyweight(key);
        Car audiA6One = new Car(flyweight, "奥迪A6", "黑色", 128);
        Car audiA6Two = new Car(flyweight, "奥迪A6", "灰色", 160);
        audiA6One.print();
        audiA6Two.print();
        width = 1.77;
        height = 1.45;
        length = 4.63;
        key = "" + width + "#" + height + "#" + length;
        flyweight = factory.getFlyweight(key);
        Car audiA4One = new Car(flyweight, "奥迪A4", "蓝色", 126);
        Car audiA4Two = new Car(flyweight, "奥迪A4", "红色", 138);
        flyweight.printMess(" 名称：奥迪A4 颜色：蓝色 功率：126"); // 向享元传递外部数据
        flyweight.printMess(" 名称：奥迪A4 颜色：红色 功率：138");

    }

}
