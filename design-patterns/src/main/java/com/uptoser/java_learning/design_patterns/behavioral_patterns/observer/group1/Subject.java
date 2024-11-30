package com.uptoser.java_learning.design_patterns.behavioral_patterns.observer.group1;

/**
 * 主题接口 subject 规定的了具体主题需要实现的添加、删除观察者以及通知观察者更新数据的方法。
 */
public interface Subject {
    public void addObserver(Observer o);
    public void deleteObserver(Observer o);
    public void notifyObservers();
}
