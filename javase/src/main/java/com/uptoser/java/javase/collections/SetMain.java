package com.uptoser.java.javase.collections;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * Set集合，它类似于一个罐子，程序可以依次把多个对象“丢进”Set集合，而Set集合通常不能记住元素的添加顺序
 * Set集合与Collection基本相同，没有提供任何额外的方法
 * Set集合不允许包含相同的元素
 * 把两个相同的元素加入同一个Set集合中，则添加操作失败，add()方法返回false
 * Set集合有HashSet、TreeSet和EnumSet三个实现类
 */
public class SetMain {

    public static void main(String[] args) {
        new SetMain().hashSet().linkedHashSet().treeSet().enumSet();
    }

    /**
     * HashSet按Hash算法来存储集合中的元素，因此具有很好的存取和查找性能
     * HashSet具有以下特点。
     * ➢ 不能保证元素的排列顺序，顺序可能与添加顺序不同，顺序也有可能发生变化。
     * ➢ HashSet不是同步的，假设有两个或者两个以上线程同时修改了HashSet集合时，则必须通过代码来保证其同步。
     * ➢ 集合元素值可以是null。
     *
     * 当向HashSet集合中存入一个元素时，HashSet会调用该对象的hashCode()方法来得到该对象的hashCode值，
     * 然后根据该hashCode值决定该对象在HashSet中的存储位置。如果有两个元素通过equals()方法比较返回true
     * ，但它们的hashCode()方法返回值不相等，HashSet将会把它们存储在不同的位置，依然可以添加成功。
     *
     * HashSet中每个能存储元素的“槽位”（slot）通常称为“桶”（bucket），如果有多个元素的hashCode值相同，
     * 但它们通过equals()方法比较返回false，就需要在一个“桶”里放多个元素，这样会导致性能下降。
     *
     * 如果向HashSet中添加一个可变对象后，后面程序修改了该可变对象的实例变量，
     * 则可能导致它与集合中的其他元素相同（即两个对象通过equals()方法比较返回true，
     * 两个对象的hashCode值也相等），这就有可能导致HashSet中包含两个相同的对象。
     */
    public SetMain hashSet(){
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(3);
        hashSet.add("zoo");
        hashSet.add("apple");
        hashSet.add(null);
//        boolean contains = hashSet.contains("3");
        System.out.println(hashSet);
        return this;
    }

    /**
     * HashSet还有一个子类LinkedHashSet，LinkedHashSet集合也是根据元素的hashCode值来决定元素的存储位置，
     * 但它同时使用链表维护元素的次序，这样使得元素看起来是以插入的顺序保存的
     * @return
     */
    public SetMain linkedHashSet(){
        LinkedHashSet sets = new LinkedHashSet();
        sets.add("111");
        sets.add("222");
        System.out.println(sets);
        //删除 111
        sets.remove("111");
        //重新添加 111
        sets.add("111");
        sets.add(null);
        System.out.println(sets);
        return this;
    }

    /**
     * TreeSet是SortedSet接口的实现类，正如SortedSet名字所暗示的，TreeSet可以确保集合元素处于排序状态。与HashSet集合相比，TreeSet还提供了如下几个额外的方法。
     * 在默认情况下，TreeSet采用自然排序
     * ➢ Comparator comparator()：如果TreeSet采用了定制排序，则该方法返回定制排序所使用的Comparator；如果TreeSet采用了自然排序，则返回null。
     * ➢ Object first()：返回集合中的第一个元素。
     * ➢ Object last()：返回集合中的最后一个元素。
     * ➢ Object lower(Object e)：返回集合中位于指定元素之前的元素（即小于指定元素的最大元素，参考元素不需要是TreeSet集合里的元素）。
     * ➢ Object higher(Object e)：返回集合中位于指定元素之后的元素（即大于指定元素的最小元素，参考元素不需要是TreeSet集合里的元素）。
     * ➢ SortedSet subSet(Object fromElement, Object toElement)：返回此Set的子集合，范围从fromElement（包含）到toElement（不包含）。
     * ➢ SortedSet headSet(Object toElement)：返回此Set的子集，由小于toElement的元素组成。
     * ➢ SortedSet tailSet(Object fromElement)：返回此Set的子集，由大于或等于fromElement的元素组成。
     *
     * 当需要把一个对象放入TreeSet中，重写该对象对应类的equals()方法时，应保证该方法与compareTo(Object obj)方法有一致的结果，
     * 其规则是：如果两个对象通过equals()方法比较返回true时，这两个对象通过compareTo(Object obj)方法比较应返回0
     *
     * 如果向TreeSet中添加一个可变对象后，并且后面程序修改了该可变对象的实例变量，这将导致它与其他对象的大小顺序发生了改变，
     * 但TreeSet不会再次调整它们的顺序，甚至可能导致TreeSet中保存的这两个对象通过compareTo(Object obj)方法比较返回0
     */
    public SetMain treeSet(){
        System.out.println("-----------------------------------");
        TreeSet nums = new TreeSet();
        //向TreeSet中添加四个Integer对象
        nums.add(5);
        nums.add(2);
        nums.add(10);
        nums.add(-9);
//        nums.add(null);//空指针
        //输出集合元素，看到集合元素已经处于排序状态
        System.out.println(nums);
        //输出集合里的第一个元素
//        System.out.println(nums.first());
        //输出集合里的最后一个元素
//        System.out.println(nums.last());
        //返回小于4的子集，不包含4
        System.out.println(nums.headSet(4));
        //返回大于5的子集，如果Set中包含5，子集中还包含5
        System.out.println(nums.tailSet(5));
        //返回大于等于-3，小于4的子集。
        System.out.println(nums.subSet(-3 , 4));

        /*
        定制排序 可以通过Comparator接口的帮助。该接口里包含一个int compare(T o1, T o2)方法，该方法用于比较o1和o2的大小：
        如果该方法返回正整数，则表明o1大于o2；如果该方法返回0，则表明o1等于o2；如果该方法返回负整数，则表明o1小于o2
         */
        TreeSet<M> ts = new TreeSet<>((M m1,M m2)->m1.age > m2.age ? -1 : m1.age < m2.age ? 1 : 0);
        ts.add(new M(5));
        ts.add(new M(-3));
        ts.add(new M(9));
        System.out.println(ts);
        System.out.println("----------------------------------------");
        return this;
    }

    /**
     * EnumSet类它提供了如下常用的类方法来创建EnumSet对象。
     * ➢ EnumSet allOf(Class elementType)：创建一个包含指定枚举类里所有枚举值的EnumSet集合。
     * ➢ EnumSet complementOf(EnumSet s)：创建一个其元素类型与指定EnumSet里元素类型相同的EnumSet集合，
     * 新EnumSet集合包含原EnumSet集合所不包含的、此枚举类剩下的枚举值。
     * ➢ EnumSet copyOf(Collection c)：使用一个普通集合来创建EnumSet集合。
     * ➢ EnumSet copyOf(EnumSet s)：创建一个与指定EnumSet具有相同元素类型、相同集合元素的EnumSet集合。
     * ➢ EnumSet noneOf(Class elementType)：创建一个元素类型为指定枚举类型的空EnumSet。
     * ➢ EnumSet of(E first, E...rest)：创建一个包含一个或多个枚举值的EnumSet集合，传入的多个枚举值必须属于同一个枚举类。
     * ➢ EnumSet range(E from, E to)：创建一个包含从from枚举值到to枚举值范围内所有枚举值的EnumSet集合。
     */
    public void enumSet(){
        //创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值
        EnumSet es1 = EnumSet.allOf(Season.class);
        //输出[SPRING,SUMMER,FALL,WINTER]
        System.out.println(es1);
        //创建一个EnumSet空集合，指定其集合元素是Season类的枚举值。
        EnumSet es2 = EnumSet.noneOf(Season.class);
        //输出[]
        System.out.println(es2);
        //手动添加两个元素
        es2.add(Season.WINTER);
        es2.add(Season.SPRING);
        //输出[SPRING,WINTER]
        System.out.println(es2);
        //以指定枚举值创建EnumSet集合
        EnumSet es3 = EnumSet.of(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,WINTER]
        System.out.println(es3);
        EnumSet es4 = EnumSet.range(Season.SUMMER , Season.WINTER);
        //输出[SUMMER,FALL,WINTER]
        System.out.println(es4);
        //新创建的EnumSet集合的元素和es4集合的元素有相同类型，
        //es5的集合元素 + es4集合元素 = Season枚举类的全部枚举值
        EnumSet es5 = EnumSet.complementOf(es4);
        //输出[SPRING]
        System.out.println(es5);

    }
    enum Season
    {
        SPRING,SUMMER,FALL,WINTER
    }
    class M
    {
        int age;
        public M(int age)
        {
            this.age = age;
        }
        public String toString()
        {
            return "M[age:" + age + "]";
        }
    }

}
