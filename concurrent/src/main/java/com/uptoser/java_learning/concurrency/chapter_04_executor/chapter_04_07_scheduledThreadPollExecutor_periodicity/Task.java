package com.uptoser.java_learning.concurrency.chapter_04_executor.chapter_04_07_scheduledThreadPollExecutor_periodicity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the task of the example. Writes a message to the
 * console with the actual date.
 * 
 * Is used to explain the utilization of an scheduled executor to execute tasks
 * periodically
 *
 */
public class Task implements Runnable {

	/**
	 * Name of the task
	 */
	private String name;

	/**
	 * execute times
	 */
	private int times;

	/**
	 * Constructor of the class
	 * 
	 * @param name
	 *            the name of the class
	 */
	public Task(String name) {
		this.name = name;
		times = 0;
	}

	/**
	 * Main method of the example. Writes a message to the console with the
	 * actual date
	 */
	@Override
	public void run() {
		times++;
		if (times == 5) {
			throw new RuntimeException("run timesi is 5;");
		}

		System.out.printf("%s: Executed at: %s --%d\n", name, new Date(), times);
		// Waits during a random period of time
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s: Ends\n", this.name);
	}

}
