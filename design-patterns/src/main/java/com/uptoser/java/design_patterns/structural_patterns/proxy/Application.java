package com.uptoser.java.design_patterns.structural_patterns.proxy;

/**
 * 在代理模式（Proxy Pattern）中，一个类代表另一个类的功能。这种类型的设计模式属于结构型模式。
 * 在代理模式中，我们创建具有现有对象的对象，以便向外界提供功能接口。
 *
 * Proxy Pattern
 * Provide a surrogate or placeholder for another object to control access to it.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Image image = new ProxyImage("真实图片.jpg");
        // 图像将从磁盘加载
        image.display();
//        Thread.sleep(1000);
//        image.display();
        // 图像不需要从磁盘加载
        Thread.sleep(2500);
        System.out.println("");
        image.display();
    }
}
