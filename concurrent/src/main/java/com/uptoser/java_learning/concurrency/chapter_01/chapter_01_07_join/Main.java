package com.uptoser.java_learning.concurrency.chapter_01.chapter_01_07_join;

import java.util.Date;

/**
 * Main class of the Example. Create and start two initialization tasks and wait
 * for their finish
 * 有些情形我们需要等待初始化资源的线程终止，再执行程序的其他任务
 */
public class Main {

	/**
	 * Main method of the class. Create and star two initialization tasks and
	 * wait for their finish
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates and starts a DataSourceLoader runnable object
		DataSourcesLoader dsLoader = new DataSourcesLoader();
		Thread thread1 = new Thread(dsLoader, "DataSourceThread");
		thread1.start();

		// Creates and starts a NetworkConnectionsLoader runnable object
		NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
		Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
		thread2.start();

		// Wait for the finalization of the two threads
		/*
		 使用join()方法等待两个线程的终止。
		 Java提供了另外两种形式的join()方法
		 join(long milliseconds) 当一个线程调用其他某个线程的join方法时，如果使用这个方法，就可以不必等待线程的运行终止，
		 如果参数指定的始终已经到达，它将继续运行。
		 join(long milliseconds，long nanos)纳秒参数
		 */
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Waits a message
		System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
	}
}
