package com.uptoser.java.concurrency.chapter_07_customize_concurrent.chapter_07_07_fork_join_task;

import java.util.concurrent.ForkJoinPool;

/**
 * Main class of the example. It creates a ForkJoinPool and a Task and executes
 * the task in the pool
 */
public class Main {
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        
        /*
         * Create an array of 10000 elements
         */
        int array[] = new int[10000];
        
        /*
         * ForkJoinPool to execute the task
         */
        ForkJoinPool pool = new ForkJoinPool();
        
        /*
         * Task to increment the elements of the array
         */
        Task task = new Task("Task", array, 0, array.length);
        
        /*
         * Send the task to the pool
         */
        pool.invoke(task);
        
        /*
         * Shutdown the pool
         */
        pool.shutdown();
        
        /*
         * Write a message in the console
         */
        System.out.printf("Main: End of the program.\n");
        
    }
    
}
