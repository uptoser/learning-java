package com.uptoser.java.design_patterns.creational_patterns.factory_method;


/**
 * 工厂方法模式:定义一个用于创建对象的接口，让子类决定实例化哪一个类。 Factory Method 使一个类的实例化延迟到其子类。
 *
 * Factory Method Pattern
 * Define an interface for creating an object, but let subclasses decide which class to
 * instantiate. Factory Method lets a class defer instantiation to subclasses.
 */
public class Application {

	public static void main(String[] args) {
		ShapeFactory shapeFactory = new RectangleShapeCreator();
		shapeFactory.getShape().draw();
		shapeFactory = new CircleShapeCreator();
		shapeFactory.getShape().draw();

	}

}
