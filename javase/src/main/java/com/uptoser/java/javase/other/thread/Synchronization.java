package com.uptoser.java.javase.other.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全问题
 */
public class Synchronization {
    public static Double balance = 100.0;

    /**
     * 同步代码块 synchronized
     */
    public void synchronizedBlock() throws InterruptedException {
        for(int i=0;i<100;i++){
            Thread save = new Thread(()->{
                synchronized (Synchronization.class){
                    Synchronization.balance += 2.0;//存2元钱
                }
            });
            Thread take = new Thread(()->{
                synchronized (Synchronization.class){
                    Synchronization.balance -= 1.0;//取1元钱
                }
            });
            save.start();
            take.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("总："+Synchronization.balance);
    }

    /**
     * Lock提供了比synchronized方法和synchronized代码块更广泛的锁定操作，
     * Lock允许实现更灵活的结构，可以具有差别很大的属性，并且支持多个相关的Condition对象。
     *
     * Lock、ReadWriteLock是Java 5提供的两个根接口，
     * 并为Lock提供了ReentrantLock（可重入锁）实现类，
     * 为ReadWriteLock提供了ReentrantReadWriteLock实现类。
     *
     * Java 8新增了新型的StampedLock类，在大多数场景中它可以替代传统的ReentrantReadWriteLock。
     * ReentrantReadWriteLock为读写操作提供了三种锁模式：Writing、ReadingOptimistic、Reading。
     *
     */
    public static ReentrantLock lock = new ReentrantLock();
    public void lock() throws InterruptedException {
        for(int i=0;i<100;i++){
            Thread save = new Thread(()->{
                Synchronization.lock.lock();
                Synchronization.balance += 2.0;//存2元钱
                Synchronization.lock.unlock();
            });
            Thread take = new Thread(()->{
                Synchronization.lock.lock();
                Synchronization.balance -= 1.0;//取1元钱
                Synchronization.lock.unlock();
            });
            save.start();
            take.start();
        }
        Thread.sleep(300);
        System.out.println("总："+Synchronization.balance);
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronization s = new Synchronization();
//        s.synchronizedBlock();
        s.lock();


    }
}
