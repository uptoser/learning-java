package com.uptoser.design_patterns.creational_patterns.singleton;

/**
 * 枚举
 * 是否 Lazy 初始化：否
 * 是否多线程安全：是
 * 支持反序列化
 */
public enum SingletonEnum {
    INSTANCE;
    public void whateverMethod() {
        System.out.println(111);
    }
}
