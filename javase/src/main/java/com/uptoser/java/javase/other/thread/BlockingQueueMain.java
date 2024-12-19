package com.uptoser.java.javase.other.thread;

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
public class BlockingQueueMain {
}
