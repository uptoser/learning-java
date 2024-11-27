package com.uptoser.concurrency.chapter_08_test_concurrent.chapter_08_07_multithreadedTC;

import edu.umd.cs.mtc.TestFramework;

/**
 * Main class of the example. Executes the test of the LinkedTransferQueue
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Throwable {

        /*
         * Create a Test object
         */
        ProducerConsumerTest test = new ProducerConsumerTest();

        /*
         * Execute the test
         */
        System.out.printf("Main: Starting the test\n");
        TestFramework.runOnce(test);
        System.out.printf("Main: The test has finished\n");

    }

}
