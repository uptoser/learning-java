package com.uptoser.java.concurrency.chapter_01.chapter_01_11_thread_group;

import java.util.concurrent.TimeUnit;

/**
 * 线程组
 */
public class Main {

	public static void main(String[] args) {
		//创建一个标志为Searcher的线程组
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result = new Result();
		SearchTask searchTask = new SearchTask(result);

		for (int i = 0; i < 5; ++i) {
			Thread thread = new Thread(threadGroup, searchTask);
			thread.start();

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//通过activeCount()获得线程组包含的线程数目
		System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
		System.out.printf("Information about the Thread Group\n");
		//通过list方法打印线程组对象信息
		threadGroup.list();

		Thread[] threads = new Thread[threadGroup.activeCount()];
		//通过enumerate方法获得线程组包含的线程列表
		threadGroup.enumerate(threads);
		for (int i = 0; i < threadGroup.activeCount(); ++i) {
			System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
		}
		//等待线程组第一个线程结束
		waitFinish(threadGroup);
		//中断组中的其余线程
		threadGroup.interrupt();
	}

	private static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount() > 4) {
			try {
				TimeUnit.SECONDS.sleep(1);
				threadGroup.list();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
