package com.uptoser.reflect.dynamic_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 * 它的优势在于不需要提供接口，只要一个非抽象类就能实现动态代理。
 */
public class CglibProxyExample implements MethodInterceptor {

    //真实对象
    private Object target = null;

    /**
     * 生成CGLIB代理对象
     * @param cls
     * @return Class类的CGLIB代理对象
     */
    public Object getProxy(Class cls){
        //CGLIB enhancer 增强类对象
        Enhancer enhancer = new Enhancer();
        //设置增强类型
        enhancer.setSuperclass(cls);
        //定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor方法
        enhancer.setCallback(this);
        //生成并返回代理对象
        return enhancer.create();
    }

    /**
     * @param proxy 代理对象
     * @param method 方法
     * @param args 方法参数
     * @param methodProxy 方法代理
     * @return 代理逻辑返回
     * @throws Throwable 异常
     */
    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用真实对象之前");
        //CGLIB反射调用真实对象方法
        Object result = methodProxy.invokeSuper(proxy,args);
        System.out.println("调用真实对象之后");
        return result;
    }

    //测试CGLIB动态代理
    public static void main(String[] args) {
        CglibProxyExample cglib = new CglibProxyExample();
        HelloWorldImpl proxy = (HelloWorldImpl) cglib.getProxy(HelloWorldImpl.class);
        proxy.sayHelloWorld();
    }


}
