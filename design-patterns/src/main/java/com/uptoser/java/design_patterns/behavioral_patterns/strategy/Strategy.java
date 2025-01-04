package com.uptoser.java.design_patterns.behavioral_patterns.strategy;

/**
 * 策略 (Strategy)
 * 策略是一个接口，该接口定义若干个算法标识，即定义了若干个抽象方法。
 */
public interface Strategy {
	public int doOperation(int num1, int num2);
}
