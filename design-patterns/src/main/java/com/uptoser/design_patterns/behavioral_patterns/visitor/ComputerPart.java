package com.uptoser.design_patterns.behavioral_patterns.visitor;

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
