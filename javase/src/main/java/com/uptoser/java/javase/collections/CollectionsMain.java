package com.uptoser.java.javase.collections;

import java.util.*;

/**
 * Java提供了一个操作Set、List和Map等集合的工具类：Collections，该工具类里提供了大量方法对集合元素进行排序、查询和修改等操作，
 * 还提供了将集合对象设置为不可变、对集合对象实现同步控制等方法
 */
public class CollectionsMain {
    public static void main(String[] args) {
        CollectionsMain c = new CollectionsMain();
        c.sortTest();
        System.out.println("-------------------------------");
        c.searchTest();
        System.out.println("-------------------------------");
        c.synchronizedTest();
        System.out.println("-------------------------------");
        c.unmodifiableTest();
        /**
         * Enumeration接口是Iterator迭代器的“古老版本”，从JDK 1.0开始，
         * Enumeration接口就已经存在了（Iterator从JDK 1.2才出现）。Enumeration接口只有两个名字很长的方法。
         * ➢ boolean hasMoreElements()：如果此迭代器还有剩下的元素，则返回true。
         * ➢ Object nextElement()：返回该迭代器的下一个元素，如果还有的话（否则抛出异常）。
         */
    }

    /**
     * Collections提供了如下三类方法来返回一个不可变的集合。
     * ➢ emptyXxx()：返回一个空的、不可变的集合对象，此处的集合既可以是List，也可以是SortedSet、Set，还可以是Map、SortedMap等。
     * ➢ singletonXxx()：返回一个只包含指定对象（只有一个或一项元素）的、不可变的集合对象，此处的集合既可以是List，还可以是Map。
     * ➢ unmodifiableXxx()：返回指定集合对象的不可变视图，此处的集合既可以是List，也可以是Set、SortedSet，还可以是Map、SorteMap等。
     */
    private void unmodifiableTest() {
        //创建一个空的、不可改变的List对象
        List unmodifiableList = Collections.emptyList();
        //创建一个只有一个元素，且不可改变的Set对象
        Set unmodifiableSet = Collections.singleton("疯狂Java讲义");
        //创建一个普通Map对象
        Map scores = new HashMap();
        scores.put("语文" , 80);
        scores.put("Java" , 82);
        //返回普通Map对象对应的不可变版本
        Map unmodifiableMap = Collections.unmodifiableMap(scores);
        //下面任意一行代码都将引发UnsupportedOperationException异常
        unmodifiableList.add("测试元素");   //①
        unmodifiableSet.add("测试元素");   //②
        unmodifiableMap.put("语文" , 90);   //③

    }

    /**
     * Collections类中提供了多个synchronizedXxx()方法，
     * 该方法可以将指定集合包装成线程同步的集合，从而可以解决多线程并发访问集合时的线程安全问题。
     */
    private void synchronizedTest() {
        //下面程序创建了四个同步的集合对象
        Collection c = Collections
                .synchronizedCollection(new ArrayList());
        List list = Collections.synchronizedList(new ArrayList());
        Set s = Collections.synchronizedSet(new HashSet());
        Map m = Collections.synchronizedMap(new HashMap());
    }

    /**
     * Collections还提供了如下常用的用于查找、替换集合元素的类方法。
     *
     * ➢ int binarySearch(List list, Object key)：使用二分搜索法搜索指定的List集合，以获得指定对象在List集合中的索引。
     * 如果要使该方法可以正常工作，则必须保证List中的元素已经处于有序状态。
     * ➢ Object max(Collection coll)：根据元素的自然顺序，返回给定集合中的最大元素。
     * ➢ Object max(Collection coll, Comparator comp)：根据Comparator指定的顺序，返回给定集合中的最大元素。
     * ➢ Object min(Collection coll)：根据元素的自然顺序，返回给定集合中的最小元素。
     * ➢ Object min(Collection coll, Comparator comp)：根据Comparator指定的顺序，返回给定集合中的最小元素。
     * ➢ void fill(List list, Object obj)：使用指定元素obj替换指定List集合中的所有元素。
     * ➢ int frequency(Collection c, Object o)：返回指定集合中指定元素的出现次数。
     * ➢ int indexOfSubList(List source, List target)：返回子List对象在父List对象中第一次出现的位置索引；如果父List中没有出现这样的子List，则返回-1。
     * ➢ int lastIndexOfSubList(List source, List target)：返回子List对象在父List对象中最后一次出现的位置索引；如果父List中没有出现这样的子List，则返回-1。
     * ➢ boolean replaceAll(List list, Object oldVal, Object newVal)：使用一个新值newVal替换List对象的所有旧值oldVal。
     */
    private void searchTest() {
        ArrayList nums = new ArrayList();
        nums.add(2);
        nums.add(-5);
        nums.add(3);
        nums.add(0);
        //输出:[2, -5, 3, 0]
        System.out.println(nums);
        //输出最大元素，将输出3
        System.out.println(Collections.max(nums));
        //输出最小元素，将输出-5
        System.out.println(Collections.min(nums));
        //将nums中的0使用1来代替
        Collections.replaceAll(nums , 0 , 1);
        //输出:[2, -5, 3, 1]
        System.out.println(nums);
        //判断-5 在List集合中出现的次数，返回1
        System.out.println(Collections.frequency(nums , -5));
        //对nums集合排序
        Collections.sort(nums);
        //输出:[-5, 1, 2, 3]
        System.out.println(nums);
        //只有排序后的List集合才可用二分法查询，输出3
        System.out.println(Collections.binarySearch(nums , 3));
    }

    /**
     * 排序操作
     * Collections提供了如下常用的类方法用于对List集合元素进行排序。
     * ➢ void reverse(List list)：反转指定List集合中元素的顺序。
     * ➢ void shuffle(List list)：对List集合元素进行随机排序（shuffle方法模拟了“洗牌”动作）。
     * ➢ void sort(List list)：根据元素的自然顺序对指定List集合的元素按升序进行排序。
     * ➢ void sort(List list, Comparator c)：根据指定Comparator产生的顺序对List集合元素进行排序。
     * ➢ void swap(List list, int i, int j)：将指定List集合中的i处元素和j处元素进行交换。
     * ➢ void rotate(List list, int distance)：当distance为正数时，将list集合的后distance个元素“整体”移到前面；
     * 当distance为负数时，将list集合的前distance个元素“整体”移到后面。该方法不会改变集合的长度。
     */
    public void sortTest(){
        ArrayList nums = new ArrayList();
        nums.add(2);
        nums.add(-5);
        nums.add(3);
        nums.add(0);
        //输出:[2, -5, 3, 0]
        System.out.println(nums);
        //将List集合元素的次序反转
        Collections.reverse(nums);
        //输出:[0, 3, -5, 2]
        System.out.println(nums);
        //将List集合元素的按自然顺序排序
        Collections.sort(nums);
        //输出:[-5, 0, 2, 3]
        System.out.println(nums);
        //将List集合元素的按随机顺序排序
        Collections.shuffle(nums);
        //每次输出的次序不固定
        System.out.println(nums);

    }

}
