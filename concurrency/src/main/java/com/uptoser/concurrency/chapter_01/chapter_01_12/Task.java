package com.uptoser.concurrency.chapter_01.chapter_01_12;

import java.util.Random;

public class Task implements Runnable {

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
