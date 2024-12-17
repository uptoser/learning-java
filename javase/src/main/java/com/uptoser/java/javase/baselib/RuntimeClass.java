package com.uptoser.java.javase.baselib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * Runtime类代表Java程序的运行时环境，每个Java程序都有一个与之对应的Runtime实例，应用程序通过该对象与其运行时环境相连。
 * 应用程序不能创建自己的Runtime实例，但可以通过getRuntime()方法获取与之关联的Runtime对象。
 */
public class RuntimeClass {
    public static void main(String[] args) throws IOException {
        //获取Java程序关联的运行时对象
        Runtime runtime = Runtime.getRuntime();
        System.out.println("处理器的数量是："+runtime.availableProcessors());
        System.out.println("空闲内存数是："+runtime.freeMemory()/1024/1024);
        //java虚拟机现在已经从操纵系统那里挖过来的内存大小，也就是java虚拟机这个进程当时所占用的 所有内存
        System.out.println("总内存数是："+runtime.totalMemory()/1024/1024);
        //java虚拟机（这个进程）能构从操纵系统那里挖到的最大的内存，以字节为单位
        System.out.println("可用最大内存数是："+runtime.maxMemory()/1024/1024);
        //运行记事本程序
        Process exec = runtime.exec("notepad.exe");
        /*
        Java 9还新增了一个ProcessHandle接口，通过该接口可获取进程的ID、父进程和后代进程；
        通过该接口的onExit()方法可在进程结束时完成某些行为。
         */

        /**
         Process对象代表由该Java程序启动的子进程。Process类提供了如下三个方法，用于让程序和其子进程进行通信。
         ➢ InputStream getErrorStream()：获取子进程的错误流。
         ➢ InputStream getInputStream()：获取子进程的输入流。
         ➢ OutputStream getOutputStream()：获取子进程的输出流。
         */
        //下面程序示范了读取其他进程的输出信息。
        // 运行javac命令，返回运行该命令的子进程
        Process p = Runtime.getRuntime().exec("javac");
        try(
                // 以p进程的错误流创建BufferedReader对象
                // 这个错误流对本程序是输入流，对p进程则是输出流
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(p.getErrorStream(), Charset.forName("GBK"))))
        {
            String buff = null;
            // 采取循环方式来读取p进程的错误输出
            while((buff = br.readLine()) != null)
            {
                System.out.println(buff);
            }
        }

       /*
       也可以通过Process的getOutputStream()方法获得向进程输入数据的流
       */
        // 运行java ReadStandard命令，返回运行该命令的子进程
        final String CLASS_PATH = ClassLoader.getSystemResource("").getPath();//classpath
        Process p1 = Runtime.getRuntime().exec("java"
                +" -classpath " + CLASS_PATH
                +" com.uptoser.java.javase.baselib.RuntimeProcessWriteMain");
        try(
//                BufferedReader bufferedReader = new BufferedReader(
//                        new InputStreamReader(p1.getErrorStream(), Charset.forName("GBK")));
                // 以p进程的输出流创建PrintStream对象
                // 这个输出流对本程序是输出流，对p进程则是输入流
                PrintStream ps = new PrintStream(p1.getOutputStream())
        ){
//            //输出错误的消息
//            String buffer;
//            while ((buffer = bufferedReader.readLine()) != null){
//                System.out.println(buffer);
//            }
            // 向ReadStandard程序写入内容，这些内容将被ReadStandard读取
            ps.println("字符串1\n你好");
            ps.println("字符串2");
        }

    }

}
