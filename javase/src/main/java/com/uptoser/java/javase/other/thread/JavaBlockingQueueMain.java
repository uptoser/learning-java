package com.uptoser.java.javase.other.thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用阻塞队列（BlockingQueue）控制线程通信
 *
 * BlockingQueue也是Queue的子接口，但它的主要用途并不是作为容器，而是作为线程同步的工具
 * BlockingQueue具有一个特征：当生产者线程试图向BlockingQueue中放入元素时，如果该队列已满，则该线程被阻塞；
 * 当消费者线程试图从BlockingQueue中取出元素时，如果该队列已空，则该线程被阻塞
 *
 * BlockingQueue提供如下两个支持阻塞的方法。
 * ➢ put(E e)：尝试把E元素放入BlockingQueue中，如果该队列的元素已满，则阻塞该线程。
 * ➢ take()：尝试从BlockingQueue的头部取出元素，如果该队列的元素已空，则阻塞该线程。
 *
 * BlockingQueue继承了Queue接口，当然也可使用Queue接口中的方法。这些方法归纳起来可分为如下三组。
 * ➢ 在队列尾部插入元素。包括add(E e)、offer(E e)和put(E e)方法，当该队列已满时，这三个方法分别会抛出异常、返回false、阻塞队列。
 * ➢ 在队列头部删除并返回删除的元素。包括remove()、poll()和take()方法。当该队列已空时，这三个方法分别会抛出异常、返回false、阻塞队列。
 * ➢ 在队列头部取出但不删除元素。包括element()和peek()方法，当队列已空时，这两个方法分别抛出异常、返回false。
 */
public class JavaBlockingQueueMain {

    /**
     * BlockingQueue包含如下5个实现类。
     * ➢ ArrayBlockingQueue：基于数组实现的BlockingQueue队列。
     * ➢ LinkedBlockingQueue：基于链表实现的BlockingQueue队列。
     * ➢ PriorityBlockingQueue：它并不是标准的阻塞队列。与前面介绍的PriorityQueue类似，
     * 该队列调用remove()、poll()、take()等方法取出元素时，并不是取出队列中存在时间最长的元素，而是队列中最小的元素。
     * PriorityBlockingQueue判断元素的大小即可根据元素（实现Comparable接口）的本身大小来自然排序，也可使用Comparator进行定制排序。
     * ➢ SynchronousQueue：同步队列。对该队列的存、取操作必须交替进行。
     * ➢ DelayQueue：它是一个特殊的BlockingQueue，底层基于PriorityBlockingQueue实现。
     * 不过，DelayQueue要求集合元素都实现Delay接口（该接口里只有一个long getDelay()方法），
     * DelayQueue根据集合元素的getDalay()方法的返回值进行排序。
     *
     * TransferQueue接口和LinkedTransferQueue实现是Java 7新增的阻塞队列
     */
    public static void main(String[] args) {
        //创建一个空间为3的交换区
        final ArrayBlockingQueue<String> swap = new ArrayBlockingQueue<>(3);
        for (int i=0;i<100;i++){
            new Thread(()-> {
                try {
                    //存入线程的名字（如果队列满了就会阻塞）
                    swap.put(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },i+"").start();
            new Thread(()-> {
                try {
                    //输出交换区中的元素（如果队列空了就会阻塞）
                    System.out.println("取出了："+swap.take());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            },"取出"+i).start();
        }



    }
}
