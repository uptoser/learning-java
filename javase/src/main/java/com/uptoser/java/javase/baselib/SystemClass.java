package com.uptoser.java.javase.baselib;

import java.util.Map;
import java.util.Properties;

/*
系统类
 */
public class SystemClass {
    public static void main(String[] args) {
        //获取所有环境变量
        Map<String, String> envs = System.getenv();
//        envs.forEach((key,value)->System.out.println(key+":"+value));
        //获取指定环境变量
        System.out.println(System.getenv("JAVA_HOME"));
        //获取所有系统属性
        Properties properties = System.getProperties();
//        properties.forEach((key,value)->System.out.println(key+":"+value));
        //获取指定系统属性
        System.out.println(System.getProperty("os.name"));
        /*
        System类的in、out和err分别代表系统的标准输入（通常是键盘）、标准输出（通常是显示器）和错误输出流，
        并提供了setIn()、setOut()和setErr()方法来改变系统的标准输入、标准输出和标准错误输出流。
         */

        /*
        System类还提供了一个identityHashCode(Object x)方法，该方法返回指定对象的精确hashCode值，
        也就是根据该对象的地址计算得到的hashCode值。当某个类的hashCode()方法被重写后，
        该类实例的hashCode()方法就不能唯一地标识该对象；但通过identityHashCode()方法返回的hashCode值，
        依然是根据该对象的地址计算得到的hashCode值。所以，如果两个对象的identityHashCode值相同，
        则两个对象绝对是同一个对象
         */
    }
}
