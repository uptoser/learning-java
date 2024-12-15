package com.uptoser.java.javase.collections;

import java.util.*;
import java.util.stream.Collectors;

/**
 * List集合代表一个元素有序、可重复的集合，集合中每个元素都有其对应的顺序索引。List集合允许使用重复元素，可以通过索引来访问指定位置的集合元素。
 *
 * List集合里比Collection增加了一些根据索引来操作集合元素的方法。
 * ➢ void add(int index, Object element)：将元素element插入到List集合的index处。
 * ➢ boolean addAll(int index, Collection c)：将集合c所包含的所有元素都插入到List集合的index处。
 * ➢ Object get(int index)：返回集合index索引处的元素。
 * ➢ int indexOf(Object o)：返回对象o在List集合中第一次出现的位置索引。
 * ➢ int lastIndexOf(Object o)：返回对象o在List集合中最后一次出现的位置索引。
 * ➢ Object remove(int index)：删除并返回index索引处的元素。
 * ➢ Object set(int index, Object element)：将index索引处的元素替换成element对象，返回被替换的旧元素。
 * ➢ List subList(int fromIndex, int toIndex)：返回从索引fromIndex（包含）到索引toIndex（不包含）处所有集合元素组成的子集合。
 * 所有的List实现类都可以调用这些方法来操作集合元素。与Set集合相比，List增加了根据索引来插入、替换和删除集合元素的方法。除此之外，Java 8还为List接口添加了如下两个默认方法。
 * ➢ void replaceAll(UnaryOperator operator)：根据operator指定的计算规则重新设置List集合的所有元素。
 * ➢ void sort(Comparator c)：根据Comparator参数对List集合的元素排序。
 */
public class ListMain {
    public static void main(String[] args) {
        new ListMain().list().queue().arrayList();

    }

    /**
     * List集合代表一个元素有序、可重复的集合，集合中每个元素都有其对应的顺序索引
     * List集合允许使用重复元素，可以通过索引来访问指定位置的集合元素
     * @return
     */
    public ListMain list(){
        List books = new ArrayList();
        //向books集合中添加三个元素
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add(new String("疯狂Android讲义"));
        List<String> tests = (List) books.stream().collect(Collectors.toList());
        System.out.println(books);
        //将新字符串对象插入在第二个位置
        books.add(1 , new String("疯狂Ajax讲义"));
        for (int i = 0 ; i < books.size() ; i++ )
        {
            System.out.println(books.get(i));
        }
        //删除第三个元素
        books.remove(2);
        System.out.println(books);
        //判断指定元素在List集合中位置：输出1，表明位于第二位
        //List判断两个对象相等只要通过equals()方法比较返回true即可
        System.out.println(books.indexOf(new String("疯狂Ajax讲义")));
        //将第二个元素替换成新的字符串对象
        books.set(1, new String("疯狂Java讲义"));
        System.out.println(books);
        //将books集合的第二个元素（包括）
        //到第三个元素（不包括）截取成子集合
        System.out.println(books.subList(1 , 2));
        System.out.println("------------------------");
        /*
         sort
         */
        System.out.println(tests);
        tests.sort((o1,o2)->o1.length()-o2.length());//按长度从小到大排序
        System.out.println(tests);
        /*
         replaceAll
         */
        tests.replaceAll(s->String.valueOf(s.length()));//返回字符串的长度
        System.out.println(tests);
        System.out.println("---------------------------");
        /**
         * List比Set还额外提供了一个listIterator()方法，该方法返回一个ListIterator对象
         * ListIterator接口在Iterator接口基础上增加了如下方法
         * ➢ boolean hasPrevious()：返回该迭代器关联的集合是否还有上一个元素。
         * ➢ Object previous()：返回该迭代器的上一个元素。
         * ➢ void add(Object o)：在指定位置插入一个元素。
         */
        String[] books1 = {
                "疯狂Java讲义",
                "轻量级Java EE企业应用实战"
        };
        List bookList = new ArrayList();
        for (int i = 0; i < books1.length ; i++ )
        {
            bookList.add(books1[i]);
        }
        ListIterator lit = bookList.listIterator();
        while (lit.hasNext())
        {
            System.out.println(lit.next());
            lit.add("-------分隔符-------");
        }
        System.out.println("=======下面开始反向迭代=======");
        while(lit.hasPrevious())
        {
            System.out.println(lit.previous());
        }
        return this;
    }

    /**
     * Queue用于模拟队列这种数据结构，队列通常是指“先进先出”（FIFO）的容器。通常，队列不允许随机访问队列中的元素。
     * Queue接口中定义了如下几个方法。
     * ➢ void add(Object e)：将指定元素加入此队列的尾部。
     * ➢ Object element()：获取队列头部的元素，但是不删除该元素。
     * ➢ boolean offer(Object e)：将指定元素加入此队列的尾部。当使用有容量限制的队列时，此方法通常比add（Object e）方法更好。
     * ➢ Object peek()：获取队列头部的元素，但是不删除该元素。如果此队列为空，则返回null。
     * ➢ Object poll()：获取队列头部的元素，并删除该元素。如果此队列为空，则返回null。
     * ➢ Object remove()：获取队列头部的元素，并删除该元素。
     * @return
     */
    public ListMain queue(){
        /*
        PriorityQueue保存队列元素的顺序并不是按加入队列的顺序，而是按队列元素的大小进行重新排序
        PriorityQueue不允许插入null元素，创建PriorityQueue队列时，传入一个Comparator对象，该对象负责对队列中的所有元素进行排序
         */
        /**
         * Deque接口是Queue接口的子接口，它代表一个双端队列
         * Deque接口里定义了一些双端队列的方法
         * ➢ void addFirst(Object e)：将指定元素插入该双端队列的开头。
         * ➢ void addLast(Object e)：将指定元素插入该双端队列的末尾。
         * ➢ Iterator descendingIterator()：返回该双端队列对应的迭代器，该迭代器将以逆向顺序来迭代队列中的元素。
         * ➢ Object getFirst()：获取但不删除双端队列的第一个元素。
         * ➢ Object getLast()：获取但不删除双端队列的最后一个元素。
         * ➢ boolean offerFirst(Object e)：将指定元素插入该双端队列的开头。
         * ➢ boolean offerLast(Object e)：将指定元素插入该双端队列的末尾。
         * ➢ Object peekFirst()：获取但不删除该双端队列的第一个元素；如果此双端队列为空，则返回null。
         * ➢ Object peekLast()：获取但不删除该双端队列的最后一个元素；如果此双端队列为空，则返回null。
         * ➢ Object pollFirst()：获取并删除该双端队列的第一个元素；如果此双端队列为空，则返回null。
         * ➢ Object pollLast()：获取并删除该双端队列的最后一个元素；如果此双端队列为空，则返回null。
         * ➢ Object pop()（栈方法）：pop出该双端队列所表示的栈的栈顶元素。相当于removeFirst()。
         * ➢ void push(Object e)（栈方法）：将一个元素push进该双端队列所表示的栈的栈顶。相当于addFirst（e）。
         * ➢ Object removeFirst()：获取并删除该双端队列的第一个元素。
         * ➢ Object removeFirstOccurrence(Object o)：删除该双端队列的第一次出现的元素o。
         * ➢ Object removeLast()：获取并删除该双端队列的最后一个元素。
         * ➢ boolean removeLastOccurrence(Object o)：删除该双端队列的最后一次出现的元素o。
         */
        /*
        Deque接口提供了一个典型的实现类：ArrayDeque.它是一个基于数组实现的双端队列
         */
        System.out.println("-----------ArrayDeque----------");
        ArrayDeque stack = new ArrayDeque();
        //依次将三个元素push入"栈"
        stack.push("1");
        stack.push("2");
        stack.push("3");
        //输出：[3, 2 , 1]
        System.out.println(stack);
        //访问第一个元素，但并不将其pop出"栈"
        System.out.println(stack.peek());
        //依然输出：[3, 2 , 1]
        System.out.println(stack);
        //pop出第一个元素，输出：3
        System.out.println(stack.pop());
        //输出：[2, 1]
        System.out.println(stack);
        /*
        LinkedList与ArrayList、ArrayDeque的实现机制完全不同,LinkedList内部以链表的形式来保存集合中的元素
         */
        System.out.println("-----------LinkedList与ArrayList----------");
        LinkedList books = new LinkedList();
        //将字符串元素加入队列的尾部
        books.offer("疯狂Java讲义");
        //将一个字符串元素加入栈的顶部
        books.push("轻量级Java EE企业应用实战");
        //将字符串元素添加到队列的头部（相当于栈的顶部）
        books.offerFirst("疯狂Android讲义");
        for (int i = 0; i < books.size() ; i++ )
        {
            System.out.println(books.get(i));
        }
        //访问、并不删除栈顶的元素
        System.out.println(books.peekFirst());
        //访问、并不删除队列的最后一个元素
        System.out.println(books.peekLast());
        //将栈顶的元素弹出“栈”
        System.out.println(books.pop());
        //下面输出将看到队列中第一个元素被删除
        System.out.println(books);
        //访问、并删除队列的最后一个元素
        System.out.println(books.pollLast());
        //下面输出将看到队列中只剩下中间一个元素：
        //轻量级Java EE企业应用实战
        System.out.println(books);

        return this;
    }

    /**
     * ArrayList或Vector对象使用initialCapacity参数来设置该数组的长度
     *
     * void ensureCapacity(int minCapacity)：将ArrayList或Vector集合的Object[]数组长度增加大于或等于minCapacity值。
     * 但集合中添加大量元素时，可使用ensureCapacity方法一次性地增加initialCapacity，这可以减少重分配的次数，从而提高性能
     * void trimToSize()：调整ArrayList或Vector集合的Object[]数组长度为当前元素的个数。调用该方法可减少ArrayList或Vector集合对象占用的存储空间。
     */
    public void arrayList(){
        /**
        实际上，Vector具有很多缺点，通常尽量少用Vector实现类。因为Vector是线程安全的，所以Vector的性能比ArrayList的性能要低
        即使需要保证List集合线程安全，也同样不推荐使用Vector实现类。Collections工具类可以将一个ArrayList变成线程安全的

         Vector还提供了一个Stack子类，它用于模拟“栈”这种数据结构，与Java中的其他集合一样，进栈出栈的都是Object，
         因此从栈中取出元素后必须进行类型转换，除非你只是使用Object具有的操作。所以Stack类里提供了如下几个方法。
         ➢ Object peek()：返回“栈”的第一个元素，但并不将该元素“pop”出栈。
         ➢ Object pop()：返回“栈”的第一个元素，并将该元素“pop”出栈。
         ➢ void push(Object item)：将一个元素“push”进栈，最后一个进“栈”的元素总是位于“栈”顶。
         它同样是线程安全的、性能较差的,如果程序需要使用“栈”这种数据结构，建议使用后ArrayDeque代替它
         */

        /**
         * Arrays.ArrayList是一个固定长度的List集合，程序只能遍历访问该集合里的元素，不可增加、删除该集合里的元素
         */
        List fixedList = Arrays.asList(1 , 2 ,3);
        //获取fixedList的实现类，将输出Arrays$ArrayList
        System.out.println(fixedList.getClass());
        System.out.println(fixedList);
        //试图增加、删除元素都会引发UnsupportedOperationException异常
        fixedList.add(4);
        fixedList.remove(1);

    }



}
