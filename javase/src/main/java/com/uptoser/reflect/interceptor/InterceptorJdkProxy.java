package com.uptoser.reflect.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterceptorJdkProxy implements InvocationHandler {
    private String interceptorClass = null;//拦截器全限定名
    private Object target;//真实对象

    public InterceptorJdkProxy(Object target,String interceptorClass){
        this.target = target;
        this.interceptorClass = interceptorClass;

    }

    public static Object bind(Object target, String interceptorClass){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new InterceptorJdkProxy(target,interceptorClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(interceptorClass == null){
            //没有设置拦截器则返回原有方法
            return method.invoke(target,args);
        }
        Object result = null;
        //通过反射生成拦截器
        Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
        //调用前置方法
        if(interceptor.before(proxy,target,method,args)){
            //反射原有对象方法
            result = method.invoke(target,args);
        }else{
            //执行around方法
            interceptor.around(proxy,target,method,args);
        }
        interceptor.after(proxy,target,method,args);
        return result;
    }
}
