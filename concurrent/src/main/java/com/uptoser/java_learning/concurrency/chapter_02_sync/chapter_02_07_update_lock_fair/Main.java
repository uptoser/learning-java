package com.uptoser.java_learning.concurrency.chapter_02_sync.chapter_02_07_update_lock_fair;

/**
 * Main class of the example
 * 修改锁的公平性
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// Creates the print queue
		PrintQueue printQueue = new PrintQueue();

		// Cretes ten jobs and the Threads to run them
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread " + i);
		}

		// Launch a thread ever 0.1 seconds
		for (int i = 0; i < 10; i++) {
			thread[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
