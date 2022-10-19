package com.uptoser.designpatterns.singleton_pattern;

/**
 * @author Share 2017.9.10
 */
public class Application {
	/*
	 * 单件/单例模式
	 */
	public static void main(String[] args) {
		Singleton s = Singleton.getInstance();
		System.out.println(s.getMsg());

		SingletonStatic instance = SingletonStatic.getInstance();
		System.out.println(instance.getMsg());

		SingletonEnum instance1 = SingletonEnum.INSTANCE;
		instance1.whateverMethod();



	}

}
