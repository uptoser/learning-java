package com.uptoser.designpatterns.builder_pattern;

/**
 * 食物条目
 */
public interface Item {
    public String name();
    public Packing packing();
    public float price();
}
