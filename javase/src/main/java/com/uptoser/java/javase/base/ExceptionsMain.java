package com.uptoser.java.javase.base;

import java.io.*;

/**
 * Java把所有的非正常情况分成两种：异常（Exception）和错误（Error），它们都继承Throwable父类
 *
 * 错误（Error），一般是指与虚拟机相关的问题，如系统崩溃、虚拟机错误、动态链接失败等,这种错误无法恢复或不可能捕获
 * 异常（Exception）被分为两大类：Checked异常和Runtime异常（运行时异常）
 * 所有的RuntimeException类及其子类的实例被称为Runtime异常；不是RuntimeException类及其子类的异常实例则被称为Checked异常
 *
 *
 */
public class ExceptionsMain {

    public static void main(String[] args) throws IOException {//使用throws声明抛出异常
        //数组越界 IndexOutOfBoundsException
        //数字格式异常 NumberFormatException
        //算术异常 ArithmeticException
        //NullPointerException异常

        /*
        多异常捕获
        捕获多种类型的异常时，多种异常类型之间用竖线（|）隔开
        捕获多种类型的异常时，异常变量有隐式的final修饰，因此程序不能对异常变量重新赋值
         */
        try{
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int c = a / b;
            System.out.println("您输入的两个数相除的结果是：" + c );
        }catch (IndexOutOfBoundsException|NumberFormatException|ArithmeticException ie){
            System.out.println("程序发生了数组越界、数字格式异常、算术异常之一");
            // 捕捉多异常时，异常变量默认有final修饰，
            // 所以下面代码有错：
//            ie = new ArithmeticException("test");
        }
        catch (Exception e){
            System.out.println("未知异常");
            // 捕捉一个类型的异常时，异常变量没有final修饰
            // 所以下面代码完全正确。
            e = new RuntimeException("test");    //②
        }finally {
            /**
             * 使用finally回收资源
             */
            System.out.println("执行了 finally 里的资源回收！");
        }
        /**
         * Java 7增强了try语句的功能——它允许在try关键字后紧跟一对圆括号
         * 圆括号可以声明、初始化一个或多个资源
         * try语句在该语句结束时自动关闭这些资源
         */
        try (
            /**
             * 为了保证try语句可以正常关闭资源，这些资源实现类必须实现AutoCloseable或Closeable接口，
             * 实现这两个接口就必须实现close()方法。
             */
            // try语句会自动关闭这两个资源。
            BufferedReader br = new BufferedReader(new FileReader("AutoCloseTest.java"));
            PrintStream ps = new PrintStream(new FileOutputStream("a.txt"))
        ){
            System.out.println(br.readLine());
            ps.println("庄生晓梦迷蝴蝶");
            /**
             * Java 9再次增强了这种try语句，Java 9不要求在try后的圆括号内声明并创建资源，
             * 只需要自动关闭的资源有final修饰或者是有效的final（effectively final），
             * Java 9允许将资源变量放在try后的圆括号内
             */
        }
        /*
        通过访问catch块的后异常形参来获得异常信息
        所有的异常对象都包含了如下几个常用方法。
        ➢ getMessage()：返回该异常的详细描述字符串。
        ➢ printStackTrace()：将该异常的跟踪栈信息输出到标准错误输出。
        ➢ printStackTrace(PrintStream s)：将该异常的跟踪栈信息输出到指定输出流。
        ➢ getStackTrace()：返回该异常的跟踪栈信息。
         */
        /**
         * 子类方法声明抛出的异常不允许比父类方法声明抛出的异常多
         */

        /*
        主动抛出异常        throw new Exception（"*******"）；
         */
    }
}
