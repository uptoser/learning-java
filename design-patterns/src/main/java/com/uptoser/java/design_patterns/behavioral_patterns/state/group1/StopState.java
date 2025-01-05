package com.uptoser.java.design_patterns.behavioral_patterns.state.group1;

public class StopState implements State {

    @Override
    public void handle() {
        System.out.println("停止");
    }
}