package com.uptoser.java.javase.other.thread;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 所有运行中的任务通常对应一个进程（Process）
 * 并发性（concurrency）和并行性（parallel）是两个概念，
 * 并行指在同一时刻，有多条指令在多个处理器上同时执行；
 * 并发指在同一时刻只能有一条指令执行，但多个进程指令被快速轮换执行，使得在宏观上具有多个进程同时执行的效果
 */
public class Application {

    /**
     * 线程的创建和启动可以分为两种
     * 继承Thread类创建线程类
     * 实现Runnable接口创建线程类
     *
     * Thread.currentThread()：currentThread()是Thread类的静态方法，该方法总是返回当前正在执行的线程对象。
     * getName()：该方法是Thread类的实例方法，该方法返回调用该方法的线程名字。
     *
     * 调用线程对象的start()方法来启动该线程。
     */
    public void threadTest(){
        System.out.println("主线程："+Thread.currentThread().getName());
        for (int i=0;i<5;i++){
            //通过Lambda表达式创建线程
            new Thread(()->System.out.println("新线程："+Thread.currentThread().getName())).start();
        }
    }

    /**
     * 有返回值的线程
     * Java 5提供了Future接口来代表Callable接口里call()方法的返回值，并为Future接口提供了一个FutureTask实现类，
     * 该实现类实现了Future接口，并实现了Runnable接口——可以作为Thread类的target。
     * 在Future接口里定义了如下几个公共方法来控制它关联的Callable任务。
     * ➢ boolean cancel(boolean mayInterruptIfRunning)：试图取消该Future里关联的Callable任务。
     * ➢ V get()：返回Callable任务里call()方法的返回值。调用该方法将导致程序阻塞，必须等到子线程结束后才会得到返回值。
     * ➢ V get(long timeout, TimeUnit unit)：返回Callable任务里call()方法的返回值。
     * 该方法让程序最多阻塞timeout和unit指定的时间，如果经过指定时间后Callable任务依然没有返回值，将会抛出TimeoutException异常。
     * ➢ boolean isCancelled()：如果在Callable任务正常完成前被取消，则返回true。
     * ➢ boolean isDone()：如果Callable任务已完成，则返回true。
     */
    public void callableTest() throws InterruptedException {
        FutureTask[] array = new FutureTask[3];
        for (int i=0;i<3;i++){
            Thread.sleep(1000);//等待1秒
            //创建有返回值的线程
            new Thread(array[i] = new FutureTask<>(System::currentTimeMillis)).start();
        }
        Arrays.stream(array).forEach(f-> {
            try {
                //输出返回值
                System.out.println(new Date((long)f.get()));
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 线程的生命周期
     * 当线程被创建并启动以后，它既不是一启动就进入了执行状态，也不是一直处于执行状态，在线程的生命周期中，
     * 它要经过新建（New）、就绪（Ready）、运行（Running）、阻塞（Blocked）和死亡（Dead）5种状态。
     * 尤其是当线程启动以后，它不可能一直“霸占”着CPU独自运行，所以CPU需要在多条线程之间切换，
     * 于是线程状态也会多次在运行、就绪之间切换。
     *
     * 当程序使用new关键字创建了一个线程之后，该线程就处于新建状态，此时它和其他的Java对象一样，仅为其分配内存，并初始化其成员变量的值。
     * 调用线程对象的start()方法之后，该线程立即进入就绪状态——就绪状态相当于“等待执行”
     * 如果处于就绪状态的线程获得了CPU，开始执行run()方法的线程执行体，则该线程处于运行状态
     * 当发生如下情况时，线程将会进入阻塞状态。
     * ➢ 线程调用sleep()方法主动放弃所占用的处理器资源。
     * ➢ 线程调用了一个阻塞式IO方法，在该方法返回之前，该线程被阻塞。
     * ➢ 线程试图获得一个同步监视器，但该同步监视器正被其他线程所持有。关于同步监视器的知识、后面将有更深入的介绍。
     * ➢ 线程在等待某个通知（notify）。
     * ➢ 程序调用了线程的suspend()方法将该线程挂起。但这个方法容易导致死锁，所以应该尽量避免使用该方法。
     * 线程会以如下三种方式结束，结束后就处于死亡状态。
     * ➢ run()或call()方法执行完成，线程正常结束。
     * ➢ 线程抛出一个未捕获的Exception或Error。
     * ➢ 直接调用该线程的stop()方法来结束该线程——该方法容易导致死锁，通常不推荐使用。
     */
    public static void main(String[] args) throws InterruptedException {
        Application a = new Application();
//        a.threadTest();
//        a.callableTest();
        System.out.println("----------------------------------");
        /**
         * ThreadLocal，是Thread Local Variable（线程局部变量）的意思
         * 线程局部变量（ThreadLocal）的功用其实非常简单，就是为每一个使用该变量的线程都提供一个变量值的副本，
         * 使每一个线程都可以独立地改变自己的副本，而不会和其他线程的副本冲突
         * ThreadLocal类的用法非常简单，它只提供了如下三个public方法。
         * ➢ T get()：返回此线程局部变量中当前线程副本中的值。
         * ➢ void remove()：删除此线程局部变量中当前线程的值。
         * ➢ void set(T value)：设置此线程局部变量中当前线程副本中的值。
         */
        class Account{
            private ThreadLocal<String> name = new ThreadLocal<>();
            public Account(String name) {this.name.set(name);}
            public String getName(){return this.name.get();}
            public void setName(String name) {this.name.set(name);}
        }

        final Account account = new Account("小王");
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                if(i==5){
                    //将账户名替换成当前线程名
                    account.setName(threadName);
                }
                System.out.println(threadName+"线程的name值是："+account.getName()+":"+i);
            }
        };
        new Thread(task,"小A").start();
        new Thread(task,"小B").start();
        Thread.sleep(100);
        System.out.println("主"+"线程的name值是："+account.getName());

    }
}
