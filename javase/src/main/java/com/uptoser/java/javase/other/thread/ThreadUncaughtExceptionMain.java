package com.uptoser.java.javase.other.thread;

/**
 * 如果线程执行过程中抛出了一个未处理异常，JVM在结束该线程之前会自动查找是否有对应的Thread.UncaughtExceptionHandler对象，
 * 如果找到该处理器对象，则会调用该对象的uncaughtException（Thread t，Throwable e）方法来处理该异常
 *
 * Thread.UncaughtExceptionHandler是Thread类的一个静态内部接口，该接口内只有一个方法：void uncaughtException（Thread t，Throwable e）
 * Thread类提供了如下两个方法来设置异常处理器。
 * ➢ static setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)：为该线程类的所有线程实例设置默认的异常处理器。
 * ➢ setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh)：为指定的线程实例设置异常处理器。
 */
public class ThreadUncaughtExceptionMain {
    /**
     * ThreadGroup类实现了Thread.UncaughtExceptionHandler接口，所以每个线程所属的线程组将会作为默认的异常处理器。
     * 当一个线程抛出未处理异常时，JVM会首先查找该异常对应的异常处理器（setUncaughtExceptionHandler()方法设置的异常处理器），
     * 如果找到该异常处理器，则将调用该异常处理器处理该异常；否则，
     * JVM将会调用该线程所属的线程组对象的uncaughtException()方法来处理该异常。线程组处理异常的默认流程如下。
     * ① 如果该线程组有父线程组，则调用父线程组的uncaughtException()方法来处理该异常。
     * ② 如果该线程实例所属的线程类有默认的异常处理器（由setDefaultUncaughtExceptionHandler()方法设置的异常处理器），那么就调用该异常处理器来处理该异常。
     * ③ 如果该异常对象是ThreadDeath的对象，则不做任何处理；否则，将异常跟踪栈的信息打印到System.err错误输出流，并结束该线程。
     */
    public static void main(String[] args) {
        class ExceptionHandler implements Thread.UncaughtExceptionHandler {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.printf("An exception has been captured\n");
                System.out.printf("Thread: %s\n", t.getId());
                System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
                System.out.printf("Stack Trace: \n");
                e.printStackTrace(System.out);
                System.out.printf("Thread status: %s\n", t.getState());
            }
        }
        // 设置主线程的异常处理器
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        int a = 5 / 0;
        System.out.println("程序正常结束！");
    }
}
