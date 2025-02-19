package com.uptoser.java.concurrency.chapter_01.chapter_01_10_threadlocal_var;

import java.util.concurrent.TimeUnit;

/**
 * Main class of the UnsafeTask. Creates a Runnable task and three Thread
 * objects that run it.
 * 线程局部变量的使用
 */
public class Main {

	/**
	 * Main method of the UnsafeTaks. Creates a Runnable task and three Thread
	 * objects that run it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates the unsafe task
		UnsafeTask task = new UnsafeTask();

		// Throw three Thread objects
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(task);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
