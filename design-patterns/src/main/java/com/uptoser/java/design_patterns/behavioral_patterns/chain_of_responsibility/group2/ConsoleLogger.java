package com.uptoser.java.design_patterns.behavioral_patterns.chain_of_responsibility.group2;

public class ConsoleLogger extends AbstractLogger{
    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
