package com.uptoser.java_learning.javase.reflect.interceptor;

import com.uptoser.java_learning.javase.reflect.dynamic_proxy.HelloWorld;
import com.uptoser.java_learning.javase.reflect.dynamic_proxy.HelloWorldImpl;

public class Application {
    public static void main(String[] args) {
        HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),"com.uptoser.reflect.interceptor.MyInterceptor");
        proxy.sayHelloWorld();
    }
}
