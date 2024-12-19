package com.uptoser.java.javase.other.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Collections提供了如下几个静态方法。
 * ➢ <T> Collection<T> synchronizedCollection(Collection<T> c)：返回指定collection对应的线程安全的collection。
 * ➢ static <T> List<T> synchronizedList(List<T> list)：返回指定List对象对应的线程安全的List对象。
 * ➢ static <K,V> Map<K,V> synchronizedMap(Map<K,V> m)：返回指定Map对象对应的线程安全的Map对象。
 * ➢ static <T> Set<T> synchronizedSet(Set<T> s)：返回指定Set对象对应的线程安全的Set对象。
 * ➢ static <K,V> SortedMap<K,V> synchronizedSortedMap(SortedMap<K,V> m)：返回指定SortedMap对象对应的线程安全的SortedMap对象。
 * ➢ static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s)：返回指定SortedSet对象对应的线程安全的SortedSet对象。
 *
 * 如果需要把某个集合包装成线程安全的集合，则应该在创建之后立即包装，如上面程序所示——当HashMap对象创建后立即被包装成线程安全的HashMap对象。
 *
 * 线程安全的集合类可分为如下两类。
 * ➢ 以Concurrent开头的集合类，如ConcurrentHashMap、ConcurrentSkipListMap、ConcurrentSkip ListSet、ConcurrentLinkedQueue和ConcurrentLinkedDeque。
 * ➢ 以CopyOnWrite开头的集合类，如CopyOnWriteArrayList、CopyOnWriteArraySet。
 * 其中以Concurrent开头的集合类代表了支持并发访问的集合，它们可以支持多个线程并发写入访问，这些写入线程的所有操作都是线程安全的，但读取操作不必锁定
 * 在默认情况下，ConcurrentHashMap支持16个线程并发写入，当有超过16个线程并发向该Map中写入数据时，可能有一些线程需要等待。实际上，程序通过设置concurrencyLevel构造参数（默认值为16）来支持更多的并发写入线程。
 * 因为ConcurrentLinkedQueue和ConcurrentHashMap支持多线程并发访问，所以当使用迭代器来遍历集合元素时，该迭代器可能不能反映出创建迭代器之后所做的修改，但程序不会抛出任何异常。
 *
 * Java 8扩展了ConcurrentHashMap的功能，Java 8为该类新增了30多个新方法，这些方法可借助于Stream和Lambda表达式支持执行聚集操作。ConcurrentHashMap新增的方法大致可分为如下三类。
 * ➢ forEach系列（forEach，forEachKey，forEachValue，forEachEntry）
 * ➢ search系列（search，searchKeys，searchValues，searchEntries）
 * ➢ reduce系列（reduce，reduceToDouble，reduceToLong，reduceKeys，reduceValues）
 * 除此之外，ConcurrentHashMap还新增了mappingCount()、newKeySet()等方法，增强后的ConcurrentHashMap更适合作为缓存实现类使用。
 *
 * 由于CopyOnWriteArraySet的底层封装了CopyOnWriteArrayList，因此它的实现机制完全类似于CopyOnWriteArrayList集合。
 * 对于CopyOnWriteArrayList集合，正如它的名字所暗示的，它采用复制底层数组的方式来实现写操作。
 *
 */
public class ThreadSafeCollections {
    public static void main(String[] args) {
        List<Object> list = Collections.synchronizedList(new ArrayList<>());

        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
    }
}
