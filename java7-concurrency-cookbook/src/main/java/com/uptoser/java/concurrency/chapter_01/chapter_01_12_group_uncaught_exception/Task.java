package com.uptoser.java.concurrency.chapter_01.chapter_01_12_group_uncaught_exception;

import java.util.Random;

public class Task implements Runnable {

	/**
	 * 在RUN方法中模拟ArithmeticException异常，当随机数生成的被除数为0将抛出异常
	 */
	public void run() {
		int result;
		Random random = new Random(Thread.currentThread().getId());
		while (true) {
			result = 1000 / ((int) (random.nextDouble() * 1000));
			System.out.printf("%s : %d\n", Thread.currentThread().getName(), result);
			if (Thread.currentThread().isInterrupted()) {
				System.out.printf("%s : Interrupted\n", Thread.currentThread().getName());
				return;
			}
		}
	}

}
