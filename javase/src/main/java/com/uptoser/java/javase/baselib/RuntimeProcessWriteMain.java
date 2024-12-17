package com.uptoser.java.javase.baselib;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class RuntimeProcessWriteMain {
    public static void main(String[] args) {
        try(
                // 使用System.in创建Scanner对象，用于获取标准输入
                Scanner sc = new Scanner(System.in);
                PrintStream ps = new PrintStream(
                        new FileOutputStream(ClassLoader.getSystemResource("").getPath()
                                + "RuntimeProcessWrite.txt")))
        {
            // 增加下面一行将只把回车作为分隔符
            sc.useDelimiter("\n");
            // 判断是否还有下一个输入项
            while(sc.hasNext())
            {
                // 输出输入项
                ps.println("键盘输入的内容是:" + new String(sc.next().getBytes("GBK"),"UTF-8") );
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
