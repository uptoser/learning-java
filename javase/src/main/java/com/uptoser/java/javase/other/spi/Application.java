package com.uptoser.java.javase.other.spi;

import java.util.ServiceLoader;

/**
 * SPI自动注册
 * 在 Java 中，服务提供者接口（Service Provider Interface，SPI）是一种用于实现可插拔组件的机制。
 * 它允许应用程序在运行时动态地发现和加载实现了特定接口的类。
 * SPI自动注册则意味着服务实现可以在不需要显式代码的情况下被发现和加载。
 */
public class Application {
    public static void main(String[] args) {
        ServiceLoader<MyService> serviceLoader = ServiceLoader.load(MyService.class);
        for (MyService service : serviceLoader) {
            service.execute();
        }
    }
}
