package com.uptoser.concurrency.chapter_02_sync.chapter_02_04_use_wait_and_notify;

/**
 * Main class of the example
 * 在同步代码中使用条件
 */
public class Main {

	/**
	 * Main method of the example
	 */
	public static void main(String[] args) {

		// Creates an event storage
		EventStorage storage = new EventStorage();

		// Creates a Producer and a Thread to run it
		Producer producer = new Producer(storage);
		Thread thread1 = new Thread(producer);

		// Creates a Consumer and a Thread to run it
		Consumer consumer = new Consumer(storage);
		Thread thread2 = new Thread(consumer);

		// Starts the thread
		thread2.start();
		thread1.start();
	}

}
