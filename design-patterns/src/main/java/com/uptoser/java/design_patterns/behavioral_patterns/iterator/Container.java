package com.uptoser.java.design_patterns.behavioral_patterns.iterator;

/**
 * 集合 (Aggregate) ：一个接口，规定了具体集合需实现的操作。
 */
public interface Container {
    public Iterator getIterator();
}
