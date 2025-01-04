package com.uptoser.java.design_patterns.creational_patterns.factory_method;

/**
 * 构造者 (Creator) ：一个接口或抽象类。构造者负责定义一个称作工厂方法的抽象方法，该方法返回具体产品类的实例。
 *
 * 具体构造者 (ConcreteCreator) ：如果构造者是 抽象类，具体构造者是构造者的子类；
 * 如果构造者是接口，具体构造者是实现构造者的类。具体构造者重写工厂方法使该方法返回具体产品的实例。
 */
public abstract class ShapeFactory {
//    ShapeFactory(){
//        getShape().draw();
//    }
    public abstract Shape getShape();
}
