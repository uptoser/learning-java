package com.uptoser.java.design_patterns.behavioral_patterns.state.group1;

/**
 * 环境 (Context) ：
 * 环境是一个类，该类含有抽象状态（ state ）声明的变量，可以引用任何具体状态类的实例。
 * 用户对该环境（ Context ）类的实例在某种状态下的行为感兴趣。
 */
public class Context {
    private State state;

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

    public void request(){
        state.handle();
    }
}