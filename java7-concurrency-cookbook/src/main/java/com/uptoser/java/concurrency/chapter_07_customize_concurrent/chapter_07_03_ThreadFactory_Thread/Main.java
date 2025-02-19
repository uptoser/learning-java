package com.uptoser.java.concurrency.chapter_07_customize_concurrent.chapter_07_03_ThreadFactory_Thread;

/**
 * Main class of the example. Creates a factory, a MyThread object to execute a
 * Task object and executes the Thread
 * 
 */
public class Main {
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /*
         * Create a Factory
         */
        MyThreadFactory myFactory = new MyThreadFactory("MyThreadFactory");
        
        /*
         * Crate a Task
         */
        MyTask task = new MyTask();
        
        /*
         * Create a Thread using the Factory to execute the Task
         */
        Thread thread = myFactory.newThread(task);
        
        /*
         * Start the Thread
         */
        thread.start();
        
        /*
         * Wait for the finalization of the Thread
         */
        thread.join();
        
        /*
         * Write the thread info to the console
         */
        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n", thread);
        System.out.printf("Main: End of the example.\n");
        
    }
    
}
