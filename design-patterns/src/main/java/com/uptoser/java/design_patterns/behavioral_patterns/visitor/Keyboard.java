package com.uptoser.java.design_patterns.behavioral_patterns.visitor;

/**
 * 具体元素 (Concrete Element) ： Element 的子类。
 */
public class Keyboard  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}