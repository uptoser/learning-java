package com.uptoser.java.javase.baselib;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 基础用户交互
 */
public class ScannerMain {
    public static void main(String[] args) throws FileNotFoundException {
        //如果使用java ScannerMain Java Spring运行，将会看到结果.
        for(String arg:args){
            System.out.println(arg);
        }
        //使用Scanner获取键盘输入
        /*
        使用Scanner类可以很方便地获取用户的键盘输入，Scanner是一个基于正则表达式的文本扫描器，
        它可以从文件、输入流、字符串中解析出基本类型值和字符串值。Scanner类提供了多个构造器，
        不同的构造器可以接收文件、输入流、字符串作为数据源，用于从文件、输入流、字符串中解析数据。
         */
//        Scanner scanner = new Scanner(System.in);
//        scanner.useDelimiter("\n");//把回车当分隔符
//        while(scanner.hasNext()){
//            System.out.println("键盘内容为："+ scanner.next());
//        }
        //读文件
        String filePath = ClassLoader.getSystemResource("read.txt").getFile();//类加载器读取classpath下文件
        Scanner scannerFile = new Scanner(new File(filePath));
        while(scannerFile.hasNextLine()){
            System.out.println(scannerFile.nextLine());
        }
    }
}
