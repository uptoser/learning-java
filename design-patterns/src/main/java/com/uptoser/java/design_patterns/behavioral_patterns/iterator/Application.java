package com.uptoser.java.design_patterns.behavioral_patterns.iterator;

/**
 * 迭代器模式（Iterator Pattern）别名：游标
 * 提供一种方法顺序访问一个聚合对象中的各个元素，而又不需要暴露该对象的内部表示。
 *
 * Iterator Pattern (Another Name: Cursor)
 * Provide a way to access the elements of an aggregate object sequentially without
 * exposing its underlying representation.
 */
public class Application {
    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}
