package com.uptoser.design_patterns.behavioral_patterns.observer.group1;

/**
 * 观察者是一个接口，该接口规定了具体观察者用来更新数据的方法。
 * 对于本问题，观察者接口规定的方法是：hearTelephone()(相当于观察者模式类图中的update()方法），
 * 即要求具体观察者都通过实现 hearTelephone()方法（模拟接听电话）来更新数据。
 */
public interface Observer {
    public void hearTelephone(String hearMess);
}
