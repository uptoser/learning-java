package com.uptoser.java.javase.classobject;

/**
 * 定义类
 *
 * 类是某一批对象的抽象,可以把类理解成某种概念
 *
 * 修饰符可以是public、final、abstract
 * 对一个类定义而言，可以包含三种最常见的成员：构造器、成员变量和方法
 */
public final class Classes {
    /*
    对定义成员变量语法格式的详细说明如下。
    ➢ 修饰符：修饰符可以省略，也可以是public、protected、private、static、final，
      其中public、protected、private三个最多只能出现其中之一，可以与static、final组合起来修饰成员变量。
    ➢ 类型：类型可以是Java语言允许的任何数据类型，包括基本类型和现在介绍的引用类型。
    ➢ 成员变量名：成员变量名只要是一个合法的标识符即可，但这只是从语法角度来说的；
      如果从程序可读性角度来看，成员变量名应该由一个或多个有意义的单词连缀而成，第一个单词首字母小写，后面每个单词首字母大写，
      其他字母全部小写，单词与单词之间不要使用任何分隔符。成员变量用于描述类或对象包含的状态数据，因此成员变量名建议使用英文名词。
    ➢ 默认值：定义成员变量还可以指定一个可选的默认值。

    ➢ private（当前类访问权限）：如果类里的一个成员（包括成员变量、方法和构造器等）使用private访问控制符来修饰，则这个成员只能在当前类的内部被访问。
    ➢ default（包访问权限）：如果类里的一个成员（包括成员变量、方法和构造器等）或者一个外部类不使用任何访问控制符修饰，就称它是包访问权限的，
    default访问控制的成员或外部类可以被相同包下的其他类访问。
    ➢ protected（子类访问权限）：如果一个成员（包括成员变量、方法和构造器等）使用protected访问控制符修饰，
    那么这个成员既可以被同一个包中的其他类访问，也可以被不同包中的子类访问。
    ➢ public（公共访问权限）：这是一个最宽松的访问控制级别，如果一个成员（包括成员变量、方法和构造器等）或者一个外部类使用public访问控制符修饰，
    那么这个成员或外部类就可以被所有类访问，不管访问类和被访问类是否处于同一个包中，是否具有父子继承关系。
     */

    private static String dogHouse;//内部使用的静态变量 静态变量属于类

    private int _int;//成员变量在对象实例化时会赋予初始值

    /*
    构造器是一个特殊的方法，定义构造器的语法格式与定义方法的语法格式很像
    ➢ 修饰符：修饰符可以省略，也可以是public、protected、private其中之一。
    ➢ 构造器名：构造器名必须和类名相同。
    ➢ 形参列表：和定义方法形参列表的格式完全相同。
     */
    Classes(){
        this.set("白色屋子");
    }

    //不同的构造器
    public Classes(String dogHouse){
        this.set(dogHouse);
        this.set();
    }

    /*
    定义方法语法格式的详细说明如下。
    ➢ 修饰符：修饰符可以省略，也可以是public、protected、private、static、final、abstract，其中public、protected、private三个最多只能出现其中之一；abstract和final最多只能出现其中之一，它们可以与static组合起来修饰方法。
    ➢ 方法返回值类型：返回值类型可以是Java语言允许的任何数据类型，包括基本类型和引用类型；如果声明了方法返回值类型，则方法体内必须有一个有效的return语句，该语句返回一个变量或一个表达式，这个变量或者表达式的类型必须与此处声明的类型匹配。除此之外，如果一个方法没有返回值，则必须使用void来声明没有返回值。
    ➢ 方法名：方法名的命名规则与成员变量的命名规则基本相同，但由于方法用于描述该类或该类的实例的行为特征或功能实现，因此通常建议方法名以英文动词开头。
    ➢ 形参列表：形参列表用于定义该方法可以接受的参数，形参列表由零组到多组“参数类型 形参名”组合而成，多组参数之间以英文逗号（,）隔开，形参类型和形参名之间以英文空格隔开。一旦在定义方法时指定了形参列表，则调用该方法时必须传入对应的参数值——谁调用方法，谁负责为形参赋值。
     */
    public void set(String dogHouse,String... names){
        int _int;//局部变量需要显示定义初始值
        Classes.dogHouse = dogHouse;
        for(String name : names){
            System.out.println("形参个数可变");
        }
    }

    //方法重载
    private void set(){
        System.out.println("实例化完成");
    }

    public String get(){
        return Classes.dogHouse;
    }

    public static void main(String[] args) {
        System.out.println(new Classes("黑色屋子").get());
        /*
        下面几个包是Java语言中的常用包。
        ➢ java.lang：这个包下包含了Java语言的核心类，如String、Math、System和Thread类等，使用这个包下的类无须使用import语句导入，系统会自动导入这个包下的所有类。
        ➢ java.util：这个包下包含了Java的大量工具类/接口和集合框架类/接口，例如Arrays和List、Set等。
        ➢ java.net：这个包下包含了一些Java网络编程相关的类/接口。
        ➢ java.io：这个包下包含了一些Java输入/输出编程相关的类/接口。
        ➢ java.text：这个包下包含了一些Java格式化相关的类。
        ➢ java.sql：这个包下包含了Java进行JDBC数据库编程的相关类/接口。
        ➢ java.awt：这个包下包含了抽象窗口工具集（Abstract Window Toolkits）的相关类/接口，这些类主要用于构建图形用户界面（GUI）程序。
        ➢ java.swing：这个包下包含了Swing图形用户界面编程的相关类/接口，这些类可用于构建平台无关的GUI程序。
         */

    }
}
