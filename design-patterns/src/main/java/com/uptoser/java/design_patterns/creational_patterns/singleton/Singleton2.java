package com.uptoser.java.design_patterns.creational_patterns.singleton;

/**
 * 饿汉式，
 * 是否 Lazy 初始化：否
 * 是否多线程安全：是
 */
public class Singleton2 {
	private static Singleton2 instance = new Singleton2();
	private Singleton2 (){}
	public static Singleton2 getInstance() {
		return instance;
	}
}
