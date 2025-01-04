package com.uptoser.java.design_patterns.behavioral_patterns.mediator;

/**
 * 同事 (Colleague) ：一个接口，规定了具体同事需要实现的方法。
 */
public interface Colleague{
    public void giveMess(String [] mess);
    public void receiverMess(String mess);
    public void setName(String name);
    public String getName();
}