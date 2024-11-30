package com.uptoser.java_learning.concurrency.chapter_05_fork_join.chapter_05_01_fork_pool_recursive_action;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. It creates a list of products, a ForkJoinPool and
 * a task to execute the actualization of products.
 *
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Create a list of products
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generate(10000);

		// Craete a task
		Task task = new Task(products, 0, products.size(), 0.20);

		// Create a ForkJoinPool
		ForkJoinPool pool = new ForkJoinPool();

		// Execute the Task
		pool.execute(task);

		// Write information about the pool
		do {
			//获取正在执行任务或窃取任务的线程个数。
			System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
			//返回线程从另一个线程的工作队列中窃取的任务总数的估计值。（Fork/Join框架使用的是工作窃取算法）
			System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
			//返回线程池的并行度。并行度，默认为CPU核心数，最小为1。为1的时候相当于单线程执行。
			System.out.printf("Main: Paralelism: %d\n", pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!task.isDone());

		// Shutdown the pool
		pool.shutdown();

		// Check if the task has completed normally
		if (task.isCompletedNormally()) {
			System.out.printf("Main: The process has completed normally.\n");
		}

		// Expected result: 12. Write products which price is not 12
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			if (product.getPrice() != 12) {
				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
			}
		}

		// End of the program
		System.out.println("Main: End of the program.\n");

	}

}
