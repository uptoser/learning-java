package com.uptoser.java.design_patterns.behavioral_patterns.visitor;

/**
 * 抽象访问者 (Visitor) ：一个接口，该接口定义操作对象 (Concrete Element 的实例）的方法。
 */
public interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}
