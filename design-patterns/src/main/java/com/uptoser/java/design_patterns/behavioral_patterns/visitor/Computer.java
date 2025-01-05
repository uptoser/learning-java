package com.uptoser.java.design_patterns.behavioral_patterns.visitor;

/**
 * 电脑整体
 */
public class Computer implements ComputerPart {
    /*
     * 对象结构 (Object Structure) ：一个集合，用于存放 Element 对象，提供遍历它自己的方法
     */
    ComputerPart[] parts;

    public Computer(){
        parts = new ComputerPart[] {new Mouse(), new Keyboard(), new Monitor(1), new Monitor(2)};
    }


    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (int i = 0; i < parts.length; i++) {
            parts[i].accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}