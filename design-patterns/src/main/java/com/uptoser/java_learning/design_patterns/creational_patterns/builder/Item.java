package com.uptoser.java_learning.design_patterns.creational_patterns.builder;

/**
 * 食物条目
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
