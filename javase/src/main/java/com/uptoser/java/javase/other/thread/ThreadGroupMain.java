package com.uptoser.java.javase.other.thread;

/**
 * 如果程序没有显式指定线程属于哪个线程组，则该线程属于默认线程组
 * 在默认情况下，子线程和创建它的父线程处于同一个线程组内
 * 一旦某个线程加入了指定线程组之后，该线程将一直属于该线程组，直到该线程死亡，线程运行中途不能改变它所属的线程组。
 *
 * Thread类提供了如下几个构造器来设置新创建的线程属于哪个线程组。
 * ➢ Thread(ThreadGroup group, Runnable target)：以target的run()方法作为线程执行体创建新线程，属于group线程组。
 * ➢ Thread(ThreadGroup group, Runnable target, String name)：以target的run()方法作为线程执行体创建新线程，该线程属于group线程组，且线程名为name。
 * ➢ Thread(ThreadGroup group, String name)：创建新线程，新线程名为name，属于group线程组。
 *
 * ThreadGroup类提供了如下两个简单的构造器来创建实例。
 * ➢ ThreadGroup(String name)：以指定的线程组名字来创建新的线程组。
 * ➢ ThreadGroup(ThreadGroup parent, String name)：以指定的名字、指定的父线程组创建一个新线程组。
 *
 * ThreadGroup类提供了如下几个常用的方法来操作整个线程组里的所有线程。
 * ➢ int activeCount()：返回此线程组中活动线程的数目。
 * ➢ interrupt()：中断此线程组中的所有线程。
 * ➢ isDaemon()：判断该线程组是否是后台线程组。
 * ➢ setDaemon(boolean daemon)：把该线程组设置成后台线程组。后台线程组具有一个特征——当后台线程组的最后一个线程执行结束或最后一个线程被销毁后，后台线程组将自动销毁。
 * ➢ setMaxPriority(int pri)：设置线程组的最高优先级。
 */
public class ThreadGroupMain {

    public static void main(String[] args) {
        //自定义线程类
        class MyThread extends Thread{
            public MyThread(String name){super(name);}
            public MyThread(ThreadGroup group, String name) {super(group, name);}
            @Override
            public void run() {
                for (int i = 0; i < 10; i++)
                    System.out.println(getName()+"线程的变量i="+i);
            }
        }

        // 获取主线程所在的线程组，这是所有线程默认的线程组
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字：" + mainGroup.getName());
        System.out.println("主线程组是否是后台线程组：" + mainGroup.isDaemon());
        new MyThread("主线程组的线程").start();
        ThreadGroup tg = new ThreadGroup("新线程组");
        tg.setDaemon(true);
        System.out.println("tg线程组是否是后台线程组：" + tg.isDaemon());
        MyThread tt = new MyThread(tg , "tg组的线程甲");
        tt.start();
        new MyThread(tg , "tg组的线程乙").start();
    }
}
