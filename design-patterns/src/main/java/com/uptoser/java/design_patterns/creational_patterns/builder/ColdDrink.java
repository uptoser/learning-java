package com.uptoser.java.design_patterns.creational_patterns.builder;
/**
 * 冷饮类
 * 创建实现 Item 接口的抽象类，该类提供了默认的功能。
 */
public abstract class ColdDrink implements Item{
    @Override
    public Packing packing() {
        //冷饮默认是用瓶子包装
        return new Bottle();
    }

    @Override
    public abstract float price();
}
