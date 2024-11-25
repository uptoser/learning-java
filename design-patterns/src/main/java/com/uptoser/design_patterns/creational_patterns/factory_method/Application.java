package com.uptoser.design_patterns.creational_patterns.factory_method;


/**
 * @author Terry Roy 2022-08-02
 */
public class Application {
	/*
	 * 工厂模式
	 */
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		//获取 Circle 的对象，并调用它的 draw 方法
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		shape1.draw();
		//获取 Rectangle 的对象，并调用它的 draw 方法
		Shape shape2 = shapeFactory.getShape("RECTANGLE");
		//调用 Rectangle 的 draw 方法
		shape2.draw();
	}

}
