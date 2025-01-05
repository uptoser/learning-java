package com.uptoser.java.design_patterns.structural_patterns.flyweight;

import java.util.HashMap;

/**
 * 享元工厂（ Flyweight Factory）
 * 享元工厂是一个类，该类的实例负责创建和管理享元对象，用户或其他对象必须请求享元工厂为它得到一个享元对象。
 * 享元工厂可以通过一个散列表（也称作共享池）来管理享元对象，当用户程序或其他若干个对象向享元工厂请求一个享元对象时，
 * 如果享元工厂的散列表中已有这样的享元对象，享元工厂就提供这个享元对象给请求者，否则就创建一个享元对象添加到散列表中，
 * 同时将该享元对象提供给请求者。显然，当若干个用户或对象请求享元工厂提供一个享元对象时，
 * 第一个用户获得该享元对象的时间可能慢一些，但是后继的用户会较快地获得这个享元对象。
 * 可以使用单列模式来设计享元工厂，即让系统中只有一个享元工厂的实例。
 * 另外，为了让享元工厂能生成享元对象，需要将具体享元类作为享元工厂的内部类。
 */
public class FlyweightFactory {
    private HashMap<String, Flyweight> hashMap;
    static FlyweightFactory factory = new FlyweightFactory();

    private FlyweightFactory() {
        hashMap = new HashMap<>();
    }

    public static FlyweightFactory getFactory() {
        return factory;
    }

    public synchronized Flyweight getFlyweight(String key) {
        if (hashMap.containsKey(key))
            return hashMap.get(key);
        else {
            double width = 0, height = 0, length = 0;
            String[] str = key.split("#");
            width = Double.parseDouble(str[0]);
            height = Double.parseDouble(str[1]);
            length = Double.parseDouble(str[2]);
            Flyweight ft = new ConcreteFlyweight(width, height, length);
            hashMap.put(key, ft);
            return ft;
        }
    }

    /**
     * 具体享元 (Concrete Flyweight) ：
     * 实现享元接口的类，该类的实例称为享元对象，或简称享元
     * 具体享元类的成员变量为享元对象的内部状态，享元对象的内部状态必须与所处的周围环境无关，
     * 即要保证使用享元对象的应用程序无法更改享元的内部状态，只有这样才能使得享元对象在系统中被共享。
     * 因为享元对象是用来共享的，所以不能允许用户各自地使用具体享元类来创建对象，这样就无法达到共享的目的，
     * 因为不同用户用具体享元类创建的对象显然是不同的，所以，具体享元类的构造方法必须是 private 的，
     * 其目的是不允许用户程序直接使用具体享元类来创建享元对象，创建和管理享元对象由享元工厂负责。
     */
    class ConcreteFlyweight implements Flyweight {
        private double width;
        private double height;
        private double length;

        private ConcreteFlyweight(double width, double height, double length) {
            this.width = width;
            this.height = height;
            this.length = length;
        }

        public double getHeight() {
            return height;
        }

        public double getWidth() {
            return width;
        }

        public double getLength() {
            return length;
        }

        public void printMess(String mess) {
            System.out.print(mess);        //输出外部数据mess
            System.out.print(" 宽度：" + width);  //输出内部数据width
            System.out.print(" 高度：" + height);
            System.out.println("长度：" + length);
        }
    }
}
