package com.uptoser.java.javase.baselib;

import java.math.BigDecimal;

/**
 * float、double两种基本浮点类型时已经指出，这两个基本类型的浮点数容易引起精度丢失
 * 为了能精确表示、计算浮点数，Java提供了
 * BigDecimal类
 */
public class BigDecimalMain {
    public static void main(String[] args) {
        BigDecimal f1 = new BigDecimal("0.05");
        BigDecimal f2 = BigDecimal.valueOf(0.01);
        BigDecimal f3 = new BigDecimal(0.05);
        System.out.println("使用String作为BigDecimal构造器参数：");
        System.out.println("0.05 + 0.01 = " + f1.add(f2));
        System.out.println("0.05 - 0.01 = " + f1.subtract(f2));
        System.out.println("0.05 * 0.01 = " + f1.multiply(f2));
        System.out.println("0.05 / 0.01 = " + f1.divide(f2));
        System.out.println("使用double作为BigDecimal构造器参数：");
        System.out.println("0.05 + 0.01 = " + f3.add(f2));
        System.out.println("0.05 - 0.01 = " + f3.subtract(f2));
        System.out.println("0.05 * 0.01 = " + f3.multiply(f2));
        System.out.println("0.05 / 0.01 = " + f3.divide(f2));
        /*
        创建BigDecimal对象时，一定要使用String对象作为构造器参数，而不是直接使用double数字
         */

        /*
        如果程序中要求对double浮点数进行加、减、乘、除基本运算，则需要先将double类型数值包装成BigDecimal对象，
        调用BigDecimal对象的方法执行运算后再将结果转换成double型变量。这是比较烦琐的过程，
        可以考虑以BigDecimal为基础定义一个Arith工具类
         */
    }

}
