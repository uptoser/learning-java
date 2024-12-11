package com.uptoser.java.javase.base;

/**
 * Java数据类型
 * Java语言是强类型（strongly typed）语言，强类型语言可以在编译时进行更严格的语法检查，从而减少编程错误。
 * Java语言支持的类型分为两类：基本类型（Primitive Type）和引用类型（Reference Type/Non-primitive Type）。
 */
public class DataTypesMain {
    /**
    以下为基本数据类型 Primitive Type
    */
    public DataTypesMain primitiveDataTypes(){
        //直接将byte或short类型的表数范围内的数赋给一个byte或short变量，系统会自动把这个整数值当成byte或者short类型来处理
        byte _byte = 127;//一个byte类型整数在内存里占8位，表数范围是-128(-2^7)～127(2^7-1)
        short _short = 128;//Stores whole numbers from -32,768(-2^15) to 32,767(2^15-1)
        //int是最常用的整数类型，因此在通常情况下，直接给出一个整数值默认就是int类型
        int	decimalVal = 8_0000_0000;//整数在内存里占32位，表数范围是-2147483648(-2^31)～2147483647(2^31-1)
        int binVal = 0b1111_0000;//二进制的整数以0b或0B开头
        int octalVal = 013;//八进制的整数以0开头
        int hexVal = 0x9F;//十六进制的整数以0x或者0X开头
        //如果希望系统把一个整数值long类型来处理，应在这个整数值后增加l或者L作为后缀。推荐使用L
        long _long = 10_0000_0000_0000L;//整数在内存里占64位，表数范围是(-2^63)～(2^63-1)

        //Java语言使用16位的Unicode字符集作为编码方式，而Unicode被设计成支持世界上所有书面语言的字符
        char _char;//Stores a single character/letter or ASCII values
        char a = 'a';//直接指定单个字符型值，字符型值必须使用单引号（'）括起来
        char upper_a = 65;//字符值可以直接指定ASCII编码值。65的值为A
        char lower_a = 65 + 32;//if you are familiar with ASCII values, you can use those to display certain characters
        char tab = '\t';/* 通过转义字符表示特殊字符型值，例如：
        \b 退格符 \u0008    \n 换行符 \u000a    \r 回车符 \u000d
        \t 制表符 \u0009    \" 双引号 \u0022    \' 单引号 \u0027     \\ 反斜线 \u005c
        Unicode前256个（'\u0000'～'\u00FF'）字符和ASCII码中的字符完全重合*/
        String apostrophe = "\u0027";//单引号

        //Java的浮点数遵循IEEE 754标准，采用二进制数据的科学计数法来表示浮点数
        //float类型代表单精度浮点数，对于float型数值，第1位是符号位，接下来8位表示指数，再接下来的23位表示尾数

        //double类型代表双精度浮点数，对于double类型数值，第1位也是符号位，接下来的11位表示指数，再接下来的52位表示尾数
        float _float = 0.5f;//	Stores fractional numbers. Sufficient for storing 6 to 7 decimal digits
        double _double = 20.55d;//	Stores fractional numbers. Sufficient for storing 15 to 16 decimal digits
        //科学记数法(Scientific Numbers)只能用在浮点类型
        float f1 = 35e3f;//表示浮点型的35000.0
        double d1 = 1.2E5d;//120000.0
        /*所有的正无穷大数值都是相等的，所有的负无穷大数值都是相等的；而NaN不与任何数值相等，甚至和NaN都不相等
        只有浮点数除以0才可以得到正无穷大或负无穷大,如果一个整数值除以0，则会抛出一个异常：ArithmeticException：/by zero（除以0异常）*/
        double positiveInfinity = Double.POSITIVE_INFINITY;//正无穷(正数除以0.0)
        float negativeInfinity = Float.NEGATIVE_INFINITY;//负无穷(负数除以0.0)
        float naN = Float.NaN;//非数(0.0除以0.0)
        //布尔类型变量
        boolean _boolean = true;//Stores true or false values
        boolean co = true?true:false; //三元运算符 conditional operator (? :)

       return this;
    }

    /**
     * 类型转换 Type Casting
     * 当一个算术表达式中包含多个基本类型的值时，整个算术表达式的数据类型将发生自动提升
     */
    private DataTypesMain javaTypeCasting(){
        //直接量（literal value，也被直译为字面值）是指在程序中通过源代码直接给出的值
        int a = 6;
        float f = a;//int可以自动转成float
        String s1 = "Hello" + 3 + 4;//结果为字符串Hello34
        String s2 = 3 + 4 + "Hello";//结果为字符串7Hello
        float f1 =  (float)(500/400*1.12);
        float f2 = (float) 3.1415d;
        int i1 = (int) f2;//结果为3
        return this;
    }

    public static void main(String[] args) {
        DataTypesMain dataTypesMain = new DataTypesMain();
        dataTypesMain.primitiveDataTypes().javaTypeCasting();

    }
}
