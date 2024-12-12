package com.uptoser.java.javase.classobject;

/**
 * 包装类
 */
public class WrapperClasses {

    public static void main(String[] args) {
        String myStr = "5.55";
        Integer myInt = 5;
        Double myDouble = 5.99;
        Character myChar = 'A';
        System.out.println(myInt.intValue());
        //当JDK提供了自动装箱和自动拆箱功能后，大大简化了基本类型变量和包装类对象之间的转换过程
        int _int = new Integer(8);
        System.out.println(_int+2);
        //包装类还可实现基本类型变量和字符串之间的转换。把字符串类型的值转换为基本类型的值有两种方式。
        System.out.println(String.valueOf(myDouble));
        System.out.println(myChar.toString());
        System.out.println(Double.parseDouble(myStr));
        System.out.println(Integer.valueOf(myChar));
        //将基本类型变量和""进行连接运算，系统会自动把基本类型变量转换成字符串
        String str = myInt + "";
        //自动装箱就是可以直接把一个基本类型值赋给一个包装类实例，在这种情况下可能会出现一些特别的情形
        Integer i1 = -1;
        Integer i2 = -1;
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i1 == i2);//返回true 系统把一个-128～127之间的整数自动装箱成Integer实例，并放入了一个名为cache的数组中缓存起来
        System.out.println(i3 == i4);//返回false
        //Java 7增强了包装类的功能，Java 7为所有的包装类都提供了一个静态的compare（xxx val1，xxx val2）方法
        System.out.println(Integer.compare(i2,i3));//return -1
        System.out.println(Boolean.compare(true,false));//return 1
        System.out.println(Integer.compare(i3,i4));//return 0
        //Java 8再次增强了这些包装类的功能，其中一个重要的增强就是支持无符号算术运算
        byte b1 = -3;
        System.out.println(Byte.toUnsignedInt(b1));//转成无符号整数
        System.out.println(Integer.parseUnsignedInt("FF",16));//使用16进制解析无符号整数
        System.out.println(Integer.toUnsignedString(-3,2));//无符号2进制字符串
        System.out.println(Integer.divideUnsigned(-2,3333));//将两个数转成无符号整数后相除
        System.out.println(Integer.remainderUnsigned(-2,7));//将两个数转成无符号整数后求余
        //==和equals
        String s1 = new String("Hello");
        String s2 = "Hello";
        String s3 = "Hello";
        String s4 = "He";
        String s5 = "llo";
        String s6 = "Hel"+"lo";
        String s7 = s4+"llo";
        final String s8 = "He";
        String s9 = s8+"llo";
        System.out.println(s1 == s2);//false
        System.out.println(s2 == s3);//true
        System.out.println(s2 == s6);//true  编译时就可以确定下来
        System.out.println(s7 == s3);//false  编译时s7不能被确定下来
        System.out.println(s9 == s3);//true  final修饰时s8则直接替换成He
        System.out.println(s2 == "H"+"e"+"l"+"l"+"o");//true
        System.out.println(s1.equals(s2));//true
        /*
        正确地重写equals()方法应该满足下列条件。
        ➢ 自反性：对任意x, x.equals(x)一定返回true。
        ➢ 对称性：对任意x和y, 如果y.equals(x)返回true，则x.equals(y)也返回true。
        ➢ 传递性：对任意x, y, z, 如果x.equals(y)返回ture，y.equals(z)返回true，则x.equals(z)一定返回true。
        ➢ 一致性：对任意x和y, 如果对象中用于等价比较的信息没有改变, 那么无论调用x.equals(y)多少次，返回的结果应该保持一致，要么一直是true，要么一直是false。
        ➢ 对任何不是null的x, x.equals(null)一定返回false。
         */




    }
}
