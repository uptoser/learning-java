package com.uptoser.java.design_patterns.creational_patterns.abstract_factory;

/**
 * 抽象产品 (Product) ：一个抽象类或接口，负责定义具体产品必须实现的方法。
 */
public abstract class Trousers{
   public abstract int getWaistSize();
   public abstract int getHeight();
   public abstract String getName(); 
}