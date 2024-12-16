package com.uptoser.java.javase.other.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * 在 JDK 动态代理中，要实现代理逻辑类必须去实现 java.lang.reflect.InvocationHandler接口，
 * 它里面定义了一个invoke方法，并提供接口数组用于下挂代理对象
 */
public class JdkProxyExample implements InvocationHandler {

    //真实对象
    private Object target = null;

    /**
     * 建立代理对象和真实对象的关系并返回代理对象
     * @param target 真实对象
     * @return 代理对象
     */
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 当前调度方法
     * @param args 当前方法参数
     * @return 代理结果返回
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("调度真实对象之前的服务");
        Object obj = method.invoke(target,args);
        System.out.println("调度真实对象之后的服务");
        return obj;
    }

    //测试jdk动态代理
    public static void main(String[] args) {
        JdkProxyExample jdk = new JdkProxyExample();
        HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
        //使用代理对象
        proxy.sayHelloWorld();
    }
}
