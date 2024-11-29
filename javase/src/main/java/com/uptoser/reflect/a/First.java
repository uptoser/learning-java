package com.uptoser.reflect.a;


public class First {
    /*
     * 当程序主动使用某个类时，如果该类还未被加载到内存中，则系统会通过加载、连接、初始化三个步骤来对该类进行初始化。
     */
    /*
    类加载指的是将类的class文件读入内存，并为之创建一个java.lang.Class对象，也就是说，当程序中使用任何类时，系统都会为之建立一个java.lang.Class对象。
    类的加载由类加载器完成，类加载器通常由JVM提供，这些类加载器也是前面所有程序运行的基础，JVM提供的这些类加载器通常被称为系统类加载器。除此之外，开发者可以通过继承ClassLoader基类来创建自己的类加载器。
     */
    /*
    当类被加载之后，系统为之生成一个对应的Class对象，接着将会进入连接阶段，连接阶段负责把类的二进制数据合并到JRE中
     */

    //使用静态初始化块为变量 b 指定初始值
    static {
        b = 6;
    }
    //直接声明a的初始值
    static int a = 5;
    //先在静态初始化块中为b变量赋值,这里再次为类变量b赋值
    static int b = 9;
    //变量c没有指定初始值，它将采用默认初始值0。
    static int c;

    /*
    JVM初始化一个类包含如下几个步骤。
    ① 假如这个类还没有被加载和连接，则程序先加载并连接该类。
    ② 假如该类的直接父类还没有被初始化，则先初始化其直接父类。
    ③ 假如类中有初始化语句，则系统依次执行这些初始化语句。
     */
    public static void main(String[] args) {
        System.out.println(First.b);
        /*
        类初始化的时机
        1.创建类的实例。为某个类创建实例的方式包括：使用new操作符来创建实例，通过反射来创建实例，通过反序列化的方式来创建实例。
        2.调用某个类的类方法（静态方法）。
        3.访问某个类或接口的类变量，或为该类变量赋值。
        4.使用反射方式来强制创建某个类或接口对应的java.lang.Class对象。例如代码：Class.forName（"Person"），如果系统还未初始化Person类，则这行代码将会导致该Person类被初始化，并返回Person类对应的java.lang.Class对象。
        5.初始化某个类的子类。当初始化某个类的子类时，该子类的所有父类都会被初始化。
        6.直接使用java.exe命令来运行某个主类。当运行某个主类时，程序会先初始化该主类。

        特殊情况：当某个类变量（也叫静态变量）使用了final修饰，而且它的值可以在编译时就确定下来，那么程序其他地方使用该类变量时，实际上并没有使用该类变量，而是相当于使用常量。
         */
    }

}
