package com.uptoser.java.design_patterns.behavioral_patterns.template_method;
/**
 * 具体模板 (Concrete Template)
 */
public class Football extends Game {

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }

    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    public void hookMethod() {
        if(isHook())
        System.out.println("Football Game Hook Method...");
    }

    @Override
    public boolean isHook() {
        return super.isHook();
    }
}