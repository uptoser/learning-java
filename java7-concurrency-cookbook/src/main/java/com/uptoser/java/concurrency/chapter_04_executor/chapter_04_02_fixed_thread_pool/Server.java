package com.uptoser.java.concurrency.chapter_04_executor.chapter_04_02_fixed_thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This class simulates a server, for example, a web server, that receives
 * requests and uses a ThreadPoolExecutor to execute those requests
 *
 */
public class Server {

	/**
	 * ThreadPoolExecutors to manage the execution of the request
	 */
	private ThreadPoolExecutor executor;

	/**
	 * Constructor of the class. Creates the executor object
	 * Executors.newSingleThreadExecutor()是创建一个单线程的执行器，一次只能执行一个任务
	 */
	public Server() {
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	}

	/**
	 * This method is called when a request to the server is made. The server
	 * uses the executor to execute the request that it receives
	 * 
	 * @param task
	 *            The request made to the server
	 */
	public void executeTask(Task task) {
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task);
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		System.out.printf("Server: Task Count: %d\n", executor.getTaskCount());
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}

	/**
	 * This method shuts down the executor
	 */
	public void endServer() {
		executor.shutdown();
	}

}
