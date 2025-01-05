package com.uptoser.java.design_patterns.behavioral_patterns.state.group1;

public class StartState implements State {

    @Override
    public void handle() {
        System.out.println("开始");
    }
}