package com.uptoser.design_patterns.creational_patterns.singleton;

/**
 * 登记式/静态内部类
 * 是否 Lazy 初始化：是
 * 是否多线程安全：是
 */
public class SingletonStatic {
    private static class SingletonHolder {
        private static final SingletonStatic INSTANCE = new SingletonStatic();
    }
    private SingletonStatic (){msg = "success";}
    private String msg;
    public static final SingletonStatic getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public String getMsg() {
        return msg;
    }
}
