package com.uptoser.java.javase.base;

/**
 * Java运算符 Java Operators
 */
public class JavaOperatorsMain {

    public static void main(String[] args) {
        /*
        算数运算符 Arithmetic Operators
         */
        double a = 10.0;
        double b = 2.5;
        double v;
        v = a + b;//加
        v = a - b;//减
        v = a * b;//乘
        v = a / b;//除 注：如果除法运算符的两个操作数都是整数类型，则计算结果也是整数 例如19/4的结果是4，而不是5
        v = a % b;//求余运算符
        v = a++ + 5;//自加运算 先把操作数放入表达式中运算，然后才把操作数加1
        System.out.println(v);
        v = ++b + 5;//自加运算 先把操作数加1，然后才把操作数放入表达式中运算
        System.out.println(v);
        a = -a;//求负
        System.out.println(a);
        //Java并没有提供其他更复杂的运算符，如果需要完成乘方、开方等运算，则可借助于java.lang.Math类的工具方法完成复杂的数学运算
        a = 2;
        b = Math.pow(a,5);//求a的5次方
        System.out.println(b);
        b = Math.sqrt(a);//求a的平方根
        System.out.println(b);
        v = Math.random();//随机数
        System.out.println(v);
        a = Math.sin(a);//正弦函数
        System.out.println(a);

        /*
        赋值运算符 Assignment Operators
        */
        int x = 10;
        x += 5;
        int y,z;
        z = y = x;
        z += 5;
        System.out.printf("%d , %d \n",y,z);

        /*
        位运算符 Bitwise Operators
         */
        int five = 0B0000_0101;//数字5
        int nine = 0B0000_1001;//数字9
        System.out.println(five & nine);//按位与,当两位同时为1时才返回1。 结果输出1
        System.out.println(five | nine);//按位或,只要有一位为1即可返回1。 结果输出13
        System.out.println("-5的二进制表示为："+Integer.toBinaryString(-five));
        System.out.println(~ -five);//按位非,将操作数的每个位（包括符号位）全部取反。 结果输出4
        System.out.println(five ^ nine);//按位异或,当两位相同时返回0，不同时返回1。 结果输出12
        System.out.println(five << 2);//左移两位。 结果输出20
        System.out.println(-five << 2);//左移两位。 结果输出-20
        System.out.println(-five >> 2);//右移两位，如果第一个操作数是负数，则左边补1。 结果输出-2
        System.out.println(-five >>> 2);//无符号右移两位。 结果输出1073741822

        /*
        比较运算符 Comparison Operators 用于判断两个变量或常量的大小，比较运算的结果是一个布尔值（true或false）。
        Java支持的比较运算符如下。
        >：大于，只支持左右两边操作数是数值类型。如果前面变量的值大于后面变量的值，则返回true。
        >=：大于等于，只支持左右两边操作数是数值类型。如果前面变量的值大于等于后面变量的值，则返回true。
        <：小于，只支持左右两边操作数是数值类型。如果前面变量的值小于后面变量的值，则返回true。
        <=：小于等于，只支持左右两边操作数是数值类型。如果前面变量的值小于等于后面变量的值，则返回true。
        ==：等于，如果进行比较的两个操作数都是数值类型，即使它们的数据类型不相同，只要它们的值相等，也都将返回true。
        !=：不等于，如果进行比较的两个操作数都是数值类型，无论它们的数据类型是否相同，只要它们的值不相等，也都将返回true。
         */
        System.out.println(5>4.0);//返回true
        System.out.println(97=='a');//返回true
        System.out.println(5==5.0);//返回true

        /*
        逻辑运算符 Logical Operators
        &&：与，前后两个操作数必须都是true才返回true，否则返回false。
        &：不短路与，作用与&&相同，但不会短路。
        ||：或，只要两个操作数中有一个是true，就可以返回true，否则返回false。
        |：不短路或，作用与||相同，但不会短路。
        ！：非，只需要一个操作数，如果操作数为true，则返回false；如果操作数为false，则返回true。
        ^：异或，当两个操作数不同时才返回true，如果两个操作数相同则返回false。
         */

        /*
        三目运算符 Conditional Operator (? :)
         */
    }
}
