package com.uptoser.java.design_patterns.behavioral_patterns.interpreter.group2;
/**
 * 抽象表达式 (AbstractExpression) ：该角色为一个接口，负责定义抽象的解释操作。
 */
public interface Node{
      public void parse(Context text);
      public void execute();
}