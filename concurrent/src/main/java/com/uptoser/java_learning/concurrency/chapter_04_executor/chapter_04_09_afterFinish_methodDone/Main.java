package com.uptoser.java_learning.concurrency.chapter_04_executor.chapter_04_09_afterFinish_methodDone;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. Creates five tasks that wait a random period of
 * time. Waits 5 seconds and cancel all the tasks. Then, write the results of
 * that tasks that haven't been cancelled.
 * 在执行器总控制任务的完成
 * 可以在任务结束的时候再执行一些代码
 * FutureTask类提供了done()方法
 */
public class Main {

	/**
	 * Main method of the class.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Create an executor
		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();

		// Create five tasks
		ResultTask resultTasks[] = new ResultTask[5];
		for (int i = 0; i < 5; i++) {
			ExecutableTask executableTask = new ExecutableTask("Task " + i);
			resultTasks[i] = new ResultTask(executableTask);
			executor.submit(resultTasks[i]);
		}

		// Sleep the thread five seconds
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		// Cancel all the tasks. In the tasks that have finished before this
		// moment, this
		// cancellation has no effects
		for (int i = 0; i < resultTasks.length; i++) {
			resultTasks[i].cancel(true);
		}

		// Write the results of those tasks that haven't been cancelled
		for (int i = 0; i < resultTasks.length; i++) {
			try {
				if (!resultTasks[i].isCancelled()) {
					System.out.printf("%s\n", resultTasks[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// Finish the executor.
		executor.shutdown();

	}

}
