package com.uptoser.java.design_patterns.behavioral_patterns.chain_of_responsibility.group2;

public class FileLogger extends AbstractLogger {

    public FileLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}