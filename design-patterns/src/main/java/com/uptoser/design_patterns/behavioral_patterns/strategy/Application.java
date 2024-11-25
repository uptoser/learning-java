package com.uptoser.design_patterns.behavioral_patterns.strategy;

/**
 * @author Share 2017.9.10
 */
public class Application {
	/*
	 * 策略模式(政策)：Strategy ConcreteStrategy Context
	 */
	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationSubtract());
		System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationMultiply());
		System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
	}

}
