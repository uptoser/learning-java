package com.uptoser.java.design_patterns.behavioral_patterns.visitor;

/**
 * 具体访问者 (Concrete Visitor) ：实现 Visitor 接口的类。
 */
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        if(monitor.getCode() == 1)
            System.out.println("Displaying Monitor 1.");
        else if(monitor.getCode() == 2)
            System.out.println("Displaying Monitor 2.");
    }
}