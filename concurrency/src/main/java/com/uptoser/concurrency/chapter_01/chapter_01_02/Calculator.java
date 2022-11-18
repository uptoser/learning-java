package com.uptoser.concurrency.chapter_01.chapter_01_02;

public class Calculator implements Runnable {

	private int number;

	public Calculator(int number) {
		super();
		this.number = number;
	}

	public void run() {
		for (int i = 1; i <= 10; ++i) {
			System.out.printf("%s: %d * %d = %d \n", Thread.currentThread().getName(), number, i, i * number);
		}
	}

}
