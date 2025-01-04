package com.uptoser.java.design_patterns.behavioral_patterns.strategy;

/**
 * 策略模式（别名：政策）
 * 定义一系列算法，把它们一个个封装起来，并且使它们可相互替换。本模式使得算法可独立于使用它的客户而变化。
 *
 * Strategy Pattern(Another Name: Policy)
 * Define a family of algorithms, encapsulate each one, and make them inter
 * changeable. Strategy lets the algorithm vary independently from clients that use it.
 *
 * 命令模式和策略模式的区别
 * 1.意图：命令模式主要是对命令进行封装和处理，策略模式则是封装算法以实现可替换性。
 * 2.结构：命令模式中包括命令接口和具体命令，策略模式中包括策略接口和具体策略。
 * 3.应用场景：命令模式多用于操作的记录和撤销，而策略模式多用于算法选择。
 *
 */
public class Application {

	public static void main(String[] args) {
		Context context = new Context(new OperationAdd());
		System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationSubtract());
		System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

		context = new Context(new OperationMultiply());
		System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
	}

}
