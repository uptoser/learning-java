package com.uptoser.java.concurrency.chapter_07_customize_concurrent.chapter_07_03_ThreadFactory_Thread;

import java.util.concurrent.TimeUnit;

/**
 * Task to be executed in the MyThread threads
 *
 */
public class MyTask implements Runnable {
    
    /**
     * Main method of the Thread. Sleeps the thread during two seconds
     */
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
