package com.uptoser.concurrency.chapter_01.chapter_01_09_thread_uncaugt_exception;

/**
 * Main class of the example. Initialize a Thread to process the uncaught
 * exceptions and starts a Task object that always throws an exception
 * 线程中不可控的异常(运行时异常)
 */
public class Main {

	/**
	 * Main method of the example. Initialize a Thread to process the uncaught
	 * exceptions and starts a Task object that always throws an exception
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates the Task
		Task task = new Task();
		// Creates the Thread
		Thread thread = new Thread(task);
		// Sets de uncaugh exceptio handler
		// 调用setUncaughtExceptionHandler方法设置线程运行时异常处理器
		thread.setUncaughtExceptionHandler(new ExceptionHandler());
		// Starts the Thread
		thread.start();

		System.out.printf("Main Thread status: %s\n", thread.getState());
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main Thread status: %s\n", thread.getState());
		System.out.printf("Main Thread has finished\n");

	}

}
