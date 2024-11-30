package com.uptoser.java_learning.concurrency.chapter_04_executor.chapter_04_08_cancel_task;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. Execute a task trough an executor, waits 2 seconds
 * and then cancel the task.
 * 在执行器中取消任务
 */
public class Main {

	/**
	 * Main method of the class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Create an executor
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		// Create a task
		Task task = new Task();

		System.out.printf("Main: Executing the Task\n");

		// Send the task to the executor
		Future<String> result = executor.submit(task);

		// Sleep during two seconds
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Cancel the task, finishing its execution
		System.out.printf("Main: Cancelling the Task\n");
		//在一个正在运行的任务中，如果参数是true则取消，如果是false则不取消。
		result.cancel(true);
		// Verify that the task has been cancelled
		System.out.printf("Main: Cancelled: %s\n", result.isCancelled());
		System.out.printf("Main: Done: %s\n", result.isDone());

		// Shutdown the executor
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");
	}

}
