package com.uptoser.java_learning.design_patterns.behavioral_patterns.visitor;

public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
