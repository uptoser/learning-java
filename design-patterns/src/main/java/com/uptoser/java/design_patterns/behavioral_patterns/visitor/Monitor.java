package com.uptoser.java.design_patterns.behavioral_patterns.visitor;
/**
 * 具体元素 (Concrete Element) ： Element 的子类。
 */
public class Monitor  implements ComputerPart {
    public Monitor( int code){
        this.code = code;
    }
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}