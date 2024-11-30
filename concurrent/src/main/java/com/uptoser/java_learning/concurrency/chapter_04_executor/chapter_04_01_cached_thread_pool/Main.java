package com.uptoser.java_learning.concurrency.chapter_04_executor.chapter_04_01_cached_thread_pool;

/**
 * Main class of the example. Creates a server and 100 request of the Task class
 * that sends to the server
 * 使用执行器框架(ExecutorFramework)创建ThreadPoolExecutor对象
 * 创建缓存线程池
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create the server
		Server server = new Server();

		// Send 100 request to the server and finish
		for (int i = 0; i < 100; i++) {
			Task task = new Task("Task " + i);
			server.executeTask(task);
		}

		server.endServer();

	}

}
