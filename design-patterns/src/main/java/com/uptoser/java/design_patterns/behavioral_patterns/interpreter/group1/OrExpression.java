package com.uptoser.java.design_patterns.behavioral_patterns.interpreter.group1;

/**
 * 非终结符表达式 (NonterminalExpression) ：实现 AbstractExpression 接口的类。
 * 文法中的每一条规则 R::=R1R2...Rn 都需要一个 NonterminalExpression 类。
 * NonterminalExpression类为文法中的非终结符号实现解释操作，
 * 该解释操作通常使用递归调用表示 Ri 到 Rn 的那些对象的解释操作。
 */
public class OrExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}