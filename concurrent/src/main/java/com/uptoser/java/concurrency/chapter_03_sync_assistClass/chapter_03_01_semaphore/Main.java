package com.uptoser.java.concurrency.chapter_03_sync_assistClass.chapter_03_01_semaphore;

/**
 * Main class of the example.
 * 信号量（Semaphore）是一种计数器，用来保护一个或者多个共享资源的访问。任何时候都可以使用Semaphore来保护临界区，因为他是一个基础的同步机制
 * 如果一个线程要访问资源，它必须先获得信号量。如果信号量的内部计数器大于0，信号量将减1，然后允许访问这个共享资源
 * 如果信号量的计数器等于0，信号量将会把线程置入休眠直至计数器大于1
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
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread " + i);
		}

		// Starts the Threads
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}

}
