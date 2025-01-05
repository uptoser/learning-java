package com.uptoser.java.design_patterns.structural_patterns.proxy;

/**
 * 虚拟图片
 */
public class VirtualImage implements Image {
    private String fileName;

    public VirtualImage(){
        this.fileName = "虚拟代理图片.png";
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

}
