package com.uptoser.concurrency.chapter_04_executor.chapter_04_07_scheduledThreadPollExecutor_periodicity;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. Send a task to the executor that will execute
 * every two seconds. Then, control the remaining time for the next execution of
 * the task
 * 在执行器中周期执行任务
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
		//initialDelay参数为任务第一次执行后的延迟时间，period参数为第二次执行的时间周期
		//还有个scheduleWithFixedRate方法的第三个参数代表上个任务结束后的间隔时间
		executor.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
	}

}
