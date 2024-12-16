package com.uptoser.java.javase.collections;

import java.util.*;

/**
 * Collection接口是List、Set和Queue接口的父接口，该接口里定义的方法既可用于操作Set集合，也可用于操作List和Queue集合。
 */
public class CollectionMain {
    /*
Collection接口里定义了如下操作集合元素的方法。
➢ boolean add(Object o)：该方法用于向集合里添加一个元素。如果集合对象被添加操作改变了，则返回true。
➢ boolean addAll(Collection c)：该方法把集合c里的所有元素添加到指定集合里。如果集合对象被添加操作改变了，则返回true。
➢ void clear()：清除集合里的所有元素，将集合长度变为0。
➢ boolean contains(Object o)：返回集合里是否包含指定元素。
➢ boolean containsAll(Collection c)：返回集合里是否包含集合c里的所有元素。
➢ boolean isEmpty()：返回集合是否为空。当集合长度为0时返回true，否则返回false。
➢ Iterator iterator()：返回一个Iterator对象，用于遍历集合里的元素。
➢ boolean remove(Object o)：删除集合中的指定元素o，当集合中包含了一个或多个元素o时，该方法只删除第一个符合条件的元素，该方法将返回true。
➢ boolean removeAll(Collection c)：从集合中删除集合c里包含的所有元素（相当于用调用该方法的集合减集合c），如果删除了一个或一个以上的元素，则该方法返回true。
➢ boolean retainAll(Collection c)：从集合中删除集合c里不包含的元素（相当于把调用该方法的集合变成该集合和集合c的交集），如果该操作改变了调用该方法的集合，则该方法返回true。
➢ int size()：该方法返回集合里元素的个数。
➢ Object[] toArray()：该方法把集合转换成一个数组，所有的集合元素变成对应的数组元素。
     */
    public void collectionTest(){
        Collection c = new ArrayList();
        //添加元素
        c.add("孙悟空");
        //虽然集合里不能放基本类型的值，但Java支持自动装箱
        c.add(6);
        System.out.println("c集合的元素个数为:" + c.size());
        //删除指定元素
        c.remove(6);
        System.out.println("c集合的元素个数为:" + c.size());
        //判断是否包含指定字符串
        System.out.println("c集合的是否包含\"孙悟空\"字符串:"
                + c.contains("孙悟空"));
        c.add("轻量级Java EE企业应用实战");
        System.out.println("c集合的元素：" + c);
        Collection books = new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        System.out.println("c集合是否完全包含books集合？"
                + c.containsAll(books));
        //用c集合减去books集合里的元素
        c.removeAll(books);
        System.out.println("c集合的元素：" + c);
        //删除c集合里所有元素
        c.clear();
        System.out.println("c集合的元素：" + c);
        //books集合里只剩下c集合里也包含的元素
        books.retainAll(c);
        System.out.println("books集合的元素:" + books);
    }

    /*
    迭代器
    如果需要创建Iterator对象，则必须有一个被迭代的集合。没有集合的Iterator仿佛无本之木，没有存在的价值

    当使用Iterator迭代访问Collection集合元素时，Collection集合里的元素不能被改变，
    只有通过Iterator的remove()方法删除上一次next()方法返回的集合元素才可以；
    否则将会引发java.util.Concurrent ModificationException异常
     */
    public void iterator(){
        System.out.println("-------------------------------");
        //创建一个集合
        java.util.Collection<String> books = new HashSet<>();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");
        books.add("疯狂Android讲义");
        //获取books集合对应的迭代器
        Iterator it = books.iterator();
        while(it.hasNext())//如果被迭代的集合元素还没有被遍历完，则返回true。
        {
            //it.next()方法返回的数据类型是Object类型，
            //需要强制类型转换
            String book = (String)it.next();//返回集合里的下一个元素。
            System.out.println(book);
            if (book.equals("疯狂Java讲义"))
            {
                //从集合中删除上一次next方法返回的元素
                it.remove();
            }
            //对book变量赋值，不会改变集合元素本身
            book = "测试字符串";   //①
        }
        System.out.println(books);
        System.out.println("-------------------------------");
        /*
        forEachRemaining(Consumer action)，这是Java 8为Iterator新增的默认方法，该方法可使用Lambda表达式来遍历集合元素
        */
        books.iterator().forEachRemaining(System.out::println);
        /*
        Java 8为Collection集合新增了一个removeIf(Predicate filter)方法，该方法将会批量删除符合filter条件的所有元素
         */
        List<Integer> integers = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 0);
        /**
         * 下面这句会抛UnsupportedOperationException 因为(Arrays.asList只包装提供的数组。无法更改数组的长度)
         */
//        integers.removeIf(x -> x.equals(5));
        //构建一个新的集合
        integers = new ArrayList<>(integers);
//        integers = integers.stream().collect(Collectors.toList());
        integers.removeIf(x -> x.equals(5));
        System.out.println("删除5后的集合为："+integers);
        books.removeIf(x -> x.length()>15);
        System.out.println("删除长度>15后的集合为："+books);

    }

    public static void main(String[] args) {
        CollectionMain a = new CollectionMain();
        a.collectionTest();
        a.iterator();
    }

}
