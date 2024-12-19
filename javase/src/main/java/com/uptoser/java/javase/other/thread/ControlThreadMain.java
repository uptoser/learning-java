package com.uptoser.java.javase.other.thread;

import java.util.concurrent.TimeUnit;

/**
 * 控制线程
 */
public class ControlThreadMain {

    /**
     * Thread提供了让一个线程等待另一个线程完成的方法——join()方法。
     * 当在某个程序执行流中调用其他线程的join()方法时，调用线程将被阻塞，
     * 直到被join()方法加入的join线程执行完为止
     *
     * join()方法有如下三种重载形式。
     * ➢ join()：等待被join的线程执行完成。
     * ➢ join(long millis)：等待被join的线程的时间最长为millis毫秒。如果在millis毫秒内被join的线程还没有执行结束，则不再等待。
     * ➢ join(long millis, int nanos)：等待被join的线程的时间最长为millis毫秒加nanos毫微秒。
     */
    public void joinTest() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("加载文件中...");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println("文件加载完成！");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        System.out.println("开始等待线程的结束");
        thread.join();
        System.out.println("等待完成");
    }

    /**
     * 后台线程（Daemon Thread）/又称为“守护线程”或“精灵线程”
     * 后台线程有个特征：如果所有的前台线程都死亡，后台线程会自动死亡。
     * 调用Thread对象的setDaemon(true)方法可将指定线程设置成后台线程。
     * 当整个虚拟机中只剩下后台线程时，程序就没有继续运行的必要了，所以虚拟机也就退出了
     */
    public void daemonThread(){
        Thread daemonThread = new Thread(()->{
            int i = 0;
            while (true)
                System.out.println(Thread.currentThread().getName()+":"+i++);
        },"守护线程");
        daemonThread.setDaemon(true);
        daemonThread.start();
        for(int i=0;i<10;i++)
            System.out.println(Thread.currentThread().getName()+":"+i);
        //程序执行到此处，前台线程 (main 线程）结束，后台线程也应该随之结束
    }

    public static void main(String[] args) throws InterruptedException {
        ControlThreadMain c = new ControlThreadMain();
//        c.joinTest();
//        c.daemonThread();
        /**
         * 如果需要让当前正在执行的线程暂停一段时间，并进入阻塞状态，则可以通过调用Thread类的静态sleep()方法来实现
         * Thread还提供了一个与sleep()方法有点相似的yield()静态方法，
         * 它也可以让当前正在执行的线程暂停，但它不会阻塞该线程，它只是将该线程转入就绪状态
         */
        Thread.sleep(100,1000);
        /**
         * 改变线程优先级
         * Thread类提供了setPriority（int newPriority）、getPriority()方法来设置和返回指定线程的优先级，其中setPriority()方法的参数可以是一个整数，范围是1～10之间，也可以使用Thread类的如下三个静态常量。
         * ➢ MAX_PRIORITY：其值是10。
         * ➢ MIN_PRIORITY：其值是1。
         * ➢ NORM_PRIORITY：其值是5。
         */

    }

}
