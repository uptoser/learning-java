package com.uptoser.java.design_patterns.creational_patterns.singleton;

/**
 * 单例模式/单件模式
 * 保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 *
 * Prototype Pattern
 * Ensure a class only has one instance, and provide a global point of access to it.
 */
public class Application {

	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		System.out.println(s.getMsg());

		SingletonStatic instance = SingletonStatic.getInstance();
		System.out.println(instance.getMsg());

		SingletonEnum instance1 = SingletonEnum.INSTANCE;
		instance1.whateverMethod();



	}

}
