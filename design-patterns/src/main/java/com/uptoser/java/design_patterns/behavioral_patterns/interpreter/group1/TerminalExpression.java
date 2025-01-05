package com.uptoser.java.design_patterns.behavioral_patterns.interpreter.group1;

/**
 * 结符表达式 (TerminalExpression) ：
 * 实现 AbstractExpression 接口的类。
 * 该类将接口中的解释操作实现为与文法中的终结符相关联的操作，
 * 即文法中每个终结符号需要一个 TerminalExpression 类
 */
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}