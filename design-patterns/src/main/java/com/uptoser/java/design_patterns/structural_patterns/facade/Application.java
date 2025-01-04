package com.uptoser.java.design_patterns.structural_patterns.facade;

/**
 * 外观模式（Facade Pattern）
 * 为系统中的一组接口提供一个一致的界面， Facade 模式定义了一个高层接口，这个接口使得这一子系统更加容易使用
 * 隐藏系统的复杂性，并向客户端提供了一个客户端可以访问复杂系统的功能。
 */
public class Application {
    public static void main(String[] args) {
        String clientAdvertisement = "月光电脑，价格6356元，联系电话：1234567";
        ClientServerFacade clientFacade = new ClientServerFacade(clientAdvertisement);
        clientFacade.doAdvertisement();
    }
}
