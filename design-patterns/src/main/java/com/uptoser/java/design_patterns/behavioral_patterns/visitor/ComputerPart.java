package com.uptoser.java.design_patterns.behavioral_patterns.visitor;

/**
 * 抽象元素 (Element) ：一个抽象类，该类定义了接收访问者的 accept 操作。
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
