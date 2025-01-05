package com.uptoser.java.design_patterns.behavioral_patterns.interpreter.group1;

/**
 * 抽象表达式 (AbstractExpression) ：该角色为一个接口，负责定义抽象的解释操作。
 */
public interface Expression {
    public boolean interpret(String context);
}
