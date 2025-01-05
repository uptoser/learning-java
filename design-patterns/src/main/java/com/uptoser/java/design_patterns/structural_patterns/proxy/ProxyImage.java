package com.uptoser.java.design_patterns.structural_patterns.proxy;

/**
 * 代理 (Proxy)
 * 代理是实现抽象主题接口的类（代理和实际主题实现了相同的接口）。
 * 代理含有主题接口声明的变量，该变量用来存放 RealSubject 角色的实例引用，
 * 这样一来，代理的实例就可以控制对它所包含的 RealSubject 角色的实例访问，即可以控制对它所代理对象的访问。
 */
public class ProxyImage implements Image{

    private Image realImage;
    private String fileName;

    public ProxyImage(String fileName){
        this.fileName = fileName;

    }

    @Override
    public void display() {
        if(realImage == null){
            //虚拟代理（如果真实的加载时间比较长，可以先给一个默认对象，在加载完成后换成真实对象）
            realImage = new VirtualImage();
            new Thread(()->{
                try {
                    System.out.println("");
                    realImage = new RealImage(fileName);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Loading completed");
            }).start();
        }
        realImage.display();
    }
}