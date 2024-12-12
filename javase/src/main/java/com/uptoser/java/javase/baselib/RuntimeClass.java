package com.uptoser.java.javase.baselib;

import java.io.IOException;

/**
 * Runtime类代表Java程序的运行时环境，每个Java程序都有一个与之对应的Runtime实例，应用程序通过该对象与其运行时环境相连。
 * 应用程序不能创建自己的Runtime实例，但可以通过getRuntime()方法获取与之关联的Runtime对象。
 */
public class RuntimeClass {
    public static void main(String[] args) {
        //获取Java程序关联的运行时对象
        Runtime runtime = Runtime.getRuntime();
        System.out.println("处理器的数量是："+runtime.availableProcessors());
        System.out.println("空闲内存数是："+runtime.freeMemory()/1024/1024);
        //java虚拟机现在已经从操纵系统那里挖过来的内存大小，也就是java虚拟机这个进程当时所占用的 所有内存
        System.out.println("总内存数是："+runtime.totalMemory()/1024/1024);
        //java虚拟机（这个进程）能构从操纵系统那里挖到的最大的内存，以字节为单位
        System.out.println("可用最大内存数是："+runtime.maxMemory()/1024/1024);
        //运行记事本程序
        try{
            runtime.exec("notepad.exe");
        }catch (IOException e){
            e.printStackTrace();
        }
        /*
        Java 9还新增了一个ProcessHandle接口，通过该接口可获取进程的ID、父进程和后代进程；
        通过该接口的onExit()方法可在进程结束时完成某些行为。
         */
    }
}
