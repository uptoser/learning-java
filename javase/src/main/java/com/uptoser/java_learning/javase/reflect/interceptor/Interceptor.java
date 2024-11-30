package com.uptoser.java_learning.javase.reflect.interceptor;

import java.lang.reflect.Method;

/**
 * 定义拦截器接口Interceptor
 *
 * ●3个方法的参数为：proxy代理对象、target真实对象、method方法、args运行方法参数。
 * ●before方法返回boolean值，它在真实对象前调用。当返回为true时，则反射真实对象的方法；当返回为false时，则调用around方法。
 * ●在before方法返回为false的情况下，调用around方法。
 * ●在反射真实对象方法或者around方法执行之后，调用after方法。
 */
public interface Interceptor {
    public boolean before(Object proxy, Object target, Method method,Object[] args);
    public void around(Object proxy, Object target, Method method,Object[] args);
    public void after(Object proxy, Object target, Method method,Object[] args);
}
