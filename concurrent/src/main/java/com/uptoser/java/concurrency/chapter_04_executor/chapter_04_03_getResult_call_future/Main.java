package com.uptoser.java.concurrency.chapter_04_executor.chapter_04_03_getResult_call_future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Main class of the example. Creates and execute ten FactorialCalculator tasks
 * in an executor controlling when they finish to write the results calculated
 * 执行任务并返回结果
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a ThreadPoolExecutor with fixed size. It has a maximun of two
		// threads
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		// List to store the Future objects that control the execution of the
		// task and
		// are used to obtain the results
		List<Future<Integer>> resultList = new ArrayList<>();

		// Create a random number generator
		Random random = new Random();
		// Create and send to the executor the ten tasks
		for (int i = 0; i < 10; i++) {
			Integer number = new Integer(random.nextInt(10));
			FactorialCalculator calculator = new FactorialCalculator(number);
			//submit()方法返回Future对象来管理任务和得到的最终结果
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}

		// Wait for the finalization of the ten tasks
		do {
			//在控制台输出信息表示任务完成的数量
			System.out.printf("Main: Number of Completed Tasks: %d\n", executor.getCompletedTaskCount());
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				//Future可以通过isDone()方法来检查任务是否已经完成
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < resultList.size());

		// Write the results
		System.out.printf("Main: Results\n");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				//如果调用result.get()方法时Future对象控制的任务未完成，那么这个方法将一直阻塞到任务完成
				number = result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Core: Task %d: %d\n", i, number);
		}

		// Shutdown the executor
		executor.shutdown();

	}

}
