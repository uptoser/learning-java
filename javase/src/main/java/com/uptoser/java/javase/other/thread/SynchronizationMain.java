package com.uptoser.java.javase.other.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全问题
 */
public class SynchronizationMain {
    public static Double balance = 100.0;

    /**
     * 同步代码块 synchronized
     *
     * Object类提供的wait()、notify()和notifyAll()三个方法，这三个方法并不属于Thread类，而是属于Object类。
     * 但这三个方法必须由同步监视器对象来调用，这可分成以下两种情况。
     * ➢ 对于使用synchronized修饰的同步方法，因为该类的默认实例（this）就是同步监视器，所以可以在同步方法中直接调用这三个方法。
     * ➢ 对于使用synchronized修饰的同步代码块，同步监视器是synchronized后括号里的对象，所以必须使用该对象调用这三个方法。
     */
    public void synchronizedBlock() throws InterruptedException {
        for(int i=0;i<100;i++){
            Thread save = new Thread(()->{
                synchronized (SynchronizationMain.class){
                    SynchronizationMain.balance += 2.0;//存2元钱
                }
            });
            Thread take = new Thread(()->{
                synchronized (SynchronizationMain.class){
                    SynchronizationMain.balance -= 1.0;//取1元钱
                }
            });
            save.start();
            take.start();
        }
        TimeUnit.SECONDS.sleep(1);
        System.out.println("总："+ SynchronizationMain.balance);
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
     * Condition实例被绑定在一个Lock对象上。要获得特定Lock实例的Condition实例，调用Lock对象的newCondition()方法即可。
     * Condition类提供了如下三个方法。
     * ➢ await()：类似于隐式同步监视器上的wait()方法，导致当前线程等待，
     * 直到其他线程调用该Condition的signal()方法或signalAll()方法来唤醒该线程。
     * 该await()方法有更多变体，如long awaitNanos（long nanosTimeout）、void awaitUninterruptibly()、awaitUntil（Date deadline）等，可以完成更丰富的等待操作。
     * ➢ signal()：唤醒在此Lock对象上等待的单个线程。如果所有线程都在该Lock对象上等待，则会选择唤醒其中一个线程。
     * 选择是任意性的。只有当前线程放弃对该Lock对象的锁定后（使用await()方法），才可以执行被唤醒的线程。
     * ➢ signalAll()：唤醒在此Lock对象上等待的所有线程。只有当前线程放弃对该Lock对象的锁定后，才可以执行被唤醒的线程。
     *
     */
    public static ReentrantLock lock = new ReentrantLock();
    public void lock() throws InterruptedException {
        for(int i=0;i<100;i++){
            Thread save = new Thread(()->{
                SynchronizationMain.lock.lock();
                SynchronizationMain.balance += 2.0;//存2元钱
                SynchronizationMain.lock.unlock();
            });
            Thread take = new Thread(()->{
                SynchronizationMain.lock.lock();
                SynchronizationMain.balance -= 1.0;//取1元钱
                SynchronizationMain.lock.unlock();
            });
            save.start();
            take.start();
        }
        Thread.sleep(300);
        System.out.println("总："+ SynchronizationMain.balance);
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizationMain s = new SynchronizationMain();
//        s.synchronizedBlock();
        s.lock();


    }
}
