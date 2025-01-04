package com.uptoser.java.design_patterns.creational_patterns.abstract_factory;

/**
 * 抽象工厂 (AbstractFactory) ：一个接口或抽象类，负责定义若干个抽象方法。
 */
public abstract class ClothesFactory{
    public abstract UpperClothes createUpperClothes(int chestSize, int height);
    public abstract Trousers createTrousers(int waistSize, int height);
}
