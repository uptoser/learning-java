package com.uptoser.java.javase.classobject;

/*
如果需要创建自定义的不可变类，可遵守如下规则。
➢ 使用private和final修饰符来修饰该类的成员变量。
➢ 提供带参数的构造器（或返回该实例的类方法），用于根据传入参数来初始化类里的成员变量。
➢ 仅为该类的成员变量提供getter方法，不要为该类的成员变量提供setter方法，因为普通方法无法修改final修饰的成员变量。
➢ 如果有必要，重写Object类的hashCode()和equals()方法。equals()方法根据关键成员变量来作为两个对象是否相等的标准，除此之外，还应该保证两个用equals()方法判断为相等的对象的hashCode()也相等。
 */

/**
 * final修饰的类不可以有子类
 * final关键字可用于修饰类、变量和方法，用于表示它修饰的类、方法和变量不可改变。
 */
public final class FinalClass {
    //
    {
        NAME = "小白";
//        ADDRESS = "Beijing";//不能再为变量赋值
//        System.out.println(NAME);//final成员变量在显式初始化之前不能直接访问，但可以通过方法来访问
    }
    static {
        TYPE = "中间产物";
    }
    /*
    final修饰的变量不可被改变，一旦获得了初始值，该final变量的值就不能被重新赋值
     */
    final String NAME;//
    private final int AGE;
    public final String ADDRESS = "Tianjin of China";//须在非静态初始化块、声明该实例变量或构造器中指定初始值
//    public final int NUM;//没有在定义成员变量时指定初始值，就完全失去了存在的意义。编译报错(java: 可能尚未初始化变量NUM)
    public static final String TYPE;//类变量：必须在静态初始化块中指定初始值或声明该类变量时指定初始值

    FinalClass(){
        this.AGE = 18;
    }

    /**
     * final修饰的方法不可被重写
     */
    final void test(){

    }

    public static void main(String[] args) {
        //final局部变量,系统不会对局部变量进行初始化。因此使用final修饰局部变量时，既可以在定义时指定默认值，也可以不指定默认值。
        final int a = 1;
        final int b;
        b = 2;
//        b = 3;//只能一次，不能重复赋值
        /*
        final引用类型变量
        使用final修饰的引用类型变量不能被重新赋值，但可以改变引用类型变量所引用对象的内容
         */
        final Encapsulation e = new Encapsulation();
//        e = new Encapsulation();//只能一次，不能重复赋值
        e.setAge(11);//可以改变对象的内容
        System.out.println(e.getAge());
        /*
        可执行“宏替换”的final变量
        对一个final变量来说，不管它是类变量、实例变量，还是局部变量，只要该变量满足三个条件，这个final变量就不再是一个变量，而是相当于一个直接量
         */
        final int c = 5;
        System.out.println(c);
        /*
        上面程序中的粗体字代码定义了一个final局部变量，并在定义该final变量时指定初始值为5。对于这个程序来说，变量a其实根本不存在，
        当程序执行System.out.println(c);代码时，实际转换为执行System.out.println(5)。
        当定义final变量时就为该变量指定了初始值，而且该初始值可以在编译时就确定下来，那么这个final变量本质上就是一个“宏变量”，
        编译器会把程序中所有用到该变量的地方直接替换成该变量的值
         */

    }
}
