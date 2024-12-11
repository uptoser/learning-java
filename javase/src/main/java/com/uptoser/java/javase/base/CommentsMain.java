package com.uptoser.java.javase.base;

/**
 * Description:
 * Java注释
 * @author 标识一个类的作者 unascribed，意为无名氏。
 * @deprecated 指名一个过期的类或成员
 * @version 指定类的版本
 */
public class CommentsMain {

    /**
     * 成员变量的注释
     */
    protected String note;

    /**
     * 用于对常量进行注释
     * {@value #NUM}
     */
    public final static int NUM = 250 ;


    /**
     *
     * @param msgHead 说明一个方法的参数
     * @param msgBody 说明一个方法的参数
     * @return 说明返回值类型
     * @throws RuntimeException 标志一个类抛出的异常
     * @exception RuntimeException 标志一个类抛出的异常
     * @see RuntimeException 指定一个到另一个主题的链接
     */
    public String messages(String msgHead,String msgBody) throws RuntimeException{
        return "success";
    }

    public static void main(String[] args) {
        // This is a comment
        System.out.println("Hello World");// This is a comment

        /*
        The code below will print the words Hello World
        to the screen, and it is amazing
        */
        System.out.println("Hello World");
    }
}
