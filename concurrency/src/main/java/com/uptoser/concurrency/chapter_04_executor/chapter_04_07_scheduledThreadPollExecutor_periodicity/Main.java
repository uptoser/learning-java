package com.uptoser.concurrency.chapter_04_executor.chapter_04_07_scheduledThreadPollExecutor_periodicity;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. Send a task to the executor that will execute
 * every two seconds. Then, control the remaining time for the next execution of
 * the task
 *
 */
public class Main {

	/**
	 * Main method of the class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a ScheduledThreadPoolExecutor
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n", new Date());

		// Create a new task and sends it to the executor. It will start with a
		// delay of 1 second and then
		// it will execute every two seconds
		Task task = new Task("Task");
		// ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2,
		// TimeUnit.SECONDS);
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
	}

}
