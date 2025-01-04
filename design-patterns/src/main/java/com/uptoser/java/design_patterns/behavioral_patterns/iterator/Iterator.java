package com.uptoser.java.design_patterns.behavioral_patterns.iterator;

/**
 * 迭代器 (Iterator) ：一个接口，规定了遍历具体集合的方法
 */
public interface  Iterator {
    public boolean hasNext();
    public Object next();
}
