package com.uptoser.java.design_patterns.behavioral_patterns.memento.origin;

import java.io.Serializable;

/**
 * 备忘录 (Memento) ：
 * 负责存储原发者状态的对象，创建备忘录的类和创建原发者的类在同一个包中，
 * 该类提供的访问数据的方法都是友好方法，
 * 使得只有和原发者在同一个包中的类的实例才可以访问备忘录中的数据。
 */
public class Memento implements Serializable {
    private long state;

    void setPositionState(long state) {
        this.state = state;
    }

    long getPositionState() {
        return state;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "state=" + state +
                '}';
    }
}
