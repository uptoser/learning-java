package com.uptoser.java.design_patterns.creational_patterns.abstract_factory;


/**
 * 抽象工厂模式（别名：配套）提供一个创建一系列或相互依赖对象的接口，而无须指定它们具体的类。
 *
 * Abstract Factory Pattern(Another Name: Kit)
 * Provide an interface for creating families of related or dependent objects without
 * specifying their concrete classes.
 */
public class Application {

	public static void main(String[] args) {
		Shop shop=new Shop();
		ClothesFactory factory=new BeijingClothesFactory();
		shop.giveSuit(factory,110,82,170);
		factory=new ShanghaiClothesFactory();
		shop.giveSuit(factory,120,88,180);
	}


}
