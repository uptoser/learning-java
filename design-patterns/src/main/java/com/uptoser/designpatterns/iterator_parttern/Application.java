package com.uptoser.designpatterns.iterator_parttern;

/**
 * 迭代器模式（Iterator Pattern）
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
