package com.uptoser.design_patterns.creational_patterns.builder;

/**
 * 汉堡类
 * 创建实现 Item 接口的抽象类，该类提供了默认的功能。
 */
public abstract class Burger implements Item{

    @Override
    public Packing packing() {
        //汉堡类目默认是用纸包装
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
