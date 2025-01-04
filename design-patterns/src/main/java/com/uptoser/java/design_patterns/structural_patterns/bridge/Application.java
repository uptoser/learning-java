package com.uptoser.java.design_patterns.structural_patterns.bridge;

/**
 * 桥接模式
 * 用于把抽象化与实现化解耦，使得二者可以独立变化。这种类型的设计模式属于结构型模式，它通过提供抽象化和实现化之间的桥接结构，来实现二者的解耦
 *
 * Bridge Pattern(Another Name: Handle-Body)
 * Decouple an abstraction from its implementation so that the two can vary independently.
 */
public class Application {

	public static void main(String[] args) {
		Shape redCircle = new Circle(100,100, 10, new RedCircle());
		Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

		redCircle.draw();
		greenCircle.draw();
	}

}
