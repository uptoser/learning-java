package com.uptoser.java_learning.concurrency.chapter_03_sync_assistClass.chapter_03_02_semaphore_many;

/**
 * Main class of the example.
 * 信号量保护一个资源的多个副本
 */
public class Main {

	/**
	 * Main method of the class. Run ten jobs in parallel that send documents to
	 * the print queue at the same time.
	 */
	public static void main(String args[]) {

		// Creates the print queue
		PrintQueue printQueue = new PrintQueue();

		// Creates ten Threads
		Thread thread[] = new Thread[12];
		for (int i = 0; i < 12; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread " + i);
		}

		// Starts the Threads
		for (int i = 0; i < 12; i++) {
			thread[i].start();
		}
	}

}
