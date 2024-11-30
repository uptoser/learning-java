package com.uptoser.java.design_patterns.behavioral_patterns.visitor;

/**
 * 访问者模式（Visitor Pattern）
 */
public class Application {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
