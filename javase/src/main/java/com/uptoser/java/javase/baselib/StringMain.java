package com.uptoser.java.javase.baselib;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringMain {
    public static void main(String[] args) throws UnsupportedEncodingException {//表明尝试使用了一个Java虚拟机（JVM）不认识的字符集编码
        String str;
        byte[] bytes = "你好".getBytes();//将该String对象转换成byte数组。
        char[] chars = {'a', 'b', 'c','d','e','f'};
        /*
        String类提供了大量构造器来创建String对象
         */
        str = new String();//创建一个包含0个字符串序列的String对象（并不是返回null）
        str = new String("Hello");//字符串直接量
        str = new String(bytes, StandardCharsets.UTF_8);//使用指定的字符集将指定的byte[]数组解码成一个新的String对象
        str = new String(bytes, Charset.forName("GBK"));//java.nio.charset包下的类
        str = new String(chars);//指定字符数组
        System.out.println(str);
        /*
        StringBuilder、StringBuffer有两个属性：length和capacity，其中length属性表示其包含的字符序列的长度。
        与String对象的length不同的是，StringBuilder、StringBuffer的length是可以改变的，
        可以通过length()、setLength(int len)方法来访问和修改其字符序列的长度。
        capacity属性表示StringBuilder的容量，capacity通常比length大，程序通常无须关心capacity属性
         */
        //StringBuilder对象来创建对应的String对象
        StringBuilder sb = new StringBuilder();
        sb = sb.append('a').append("bcd").insert(0, "hello ").delete(1,3).replace(3,5,"11111");
//        sb.reverse();//反转
        System.out.println(new String(sb));
        //StringBuffer 对象来创建对应的String对象
        //StringBuffer与其用法完全相同，只是StringBuffer是线程安全的
        /*
        String类也提供了大量方法来操作字符串对象
         */
        char b = str.charAt(1);//获取字符串中指定位置的字符
        System.out.println(b);
        /*
        ➢int compareTo(String anotherString)：比较两个字符串的大小。
        如果两个字符串的字符序列相等，则返回0；
        不相等时，从两个字符串第0个字符开始比较，返回第一个不相等的字符差。
        另一种情况，较长字符串的前面部分恰巧是较短的字符串，则返回它们的长度差。

        ➢String concat(String str)：将该String对象与str连接在一起。
        与Java提供的字符串连接运算符“+”的功能相同。

        ➢boolean contentEquals(StringBuffer sb)：将该String对象与StringBuffer对象sb进行比较，当它们包含的字符序列相同时返回true。
        ➢ static String copyValueOf(char[] data)：将字符数组连缀成字符串，与String（char[] content）构造器的功能相同。

        ➢ String toLowerCase()：将字符串转换成小写。
        ➢ String toUpperCase()：将字符串转换成大写。
         */
        str = "QWERTY";
        System.out.println(str.startsWith("QWE"));
        System.out.println(str.endsWith("y"));//返回该String对象是否以suffix结尾
        str.equalsIgnoreCase("qweRty");//忽略字符的大小写的判断是否相等
        str = "331158911";
        System.out.println(str.indexOf('1'));//找出ch字符在该字符串中第一次出现的位置
        System.out.println(str.indexOf("11",3));//找出字符串在该字符串中从fromIndex开始后第一次出现的位置
        System.out.println(str.lastIndexOf('1'));//找出ch字符在该字符串中最后一次出现的位置
        str.length();//返回当前字符串长度。
        String replace = str.replace("11", "22");//将字符串中的11替换成22
        System.out.println(replace);
        replace.toCharArray();//返回char数组
        String substring = str.substring(1, 3);//截取字符串
        System.out.println(substring);


    }
}
