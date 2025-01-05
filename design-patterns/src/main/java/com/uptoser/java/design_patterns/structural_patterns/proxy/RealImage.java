package com.uptoser.java.design_patterns.structural_patterns.proxy;

/**
 * 实际主题 (RealSubject)
 */
public class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) throws InterruptedException {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) throws InterruptedException {
        System.out.println("Loading " + fileName);
        Thread.sleep(1500);
    }
}
