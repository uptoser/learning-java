package com.uptoser.java.javase.other.reflect.dynamic_proxy;

public class HelloWorldImpl implements HelloWorld{
    @Override
    public Integer sayHelloWorld() {
        System.out.println("Hello World !!!");
        return 123;
    }
}
