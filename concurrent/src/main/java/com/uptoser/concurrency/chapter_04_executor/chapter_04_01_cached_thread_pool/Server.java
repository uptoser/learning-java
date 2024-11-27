package com.uptoser.concurrency.chapter_04_executor.chapter_04_01_cached_thread_pool;

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
	 * 线程池执行器
	 */
	private ThreadPoolExecutor executor;

	/**
	 * Constructor of the class. Creates the executor object
	 */
	public Server() {
		//用Executors工厂类来创建，它返回一个ExecutorService对象，强转成ThreadPoolExecutor对象。也可以用ThreadPoolExecutor的四个构造器来创建
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
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
		//一旦创建了执行器，就可以用执行器的execute()方法执行线程
		executor.execute(task);
		//返回执行器线程池中实际的线程数
		System.out.printf("Server: Pool Size: %d\n", executor.getPoolSize());
		//返回执行器中正在执行的线程数
		System.out.printf("Server: Active Count: %d\n", executor.getActiveCount());
		//返回执行器中已经完成的线程数
		System.out.printf("Server: Completed Tasks: %d\n", executor.getCompletedTaskCount());
	}

	/**
	 * This method shuts down the executor
	 * 执行器需要显示的去结束它，否则执行器会继续执行，它将等待新任务的到来
	 * 如果执行器已经结束，在去给他发送任务，执行器将抛出RejectedExecutionException异常
	 * isShutdown()：如果调用了shutdown()将返回true
	 * isTerminated()：如果执行器关闭了将返回true
	 */
	public void endServer() {
		executor.shutdown();
	}

}
