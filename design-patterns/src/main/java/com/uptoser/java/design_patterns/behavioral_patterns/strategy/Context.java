package com.uptoser.java.design_patterns.behavioral_patterns.strategy;

/**
 * 上下文 (Context)
 * 上下文是依赖于策略接口的类，即上下文包含有策略声明的变量。
 * 上下文中提供一个方法，该方法委托策略变量调用具体策略所实现的策略接口中的方法。
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
