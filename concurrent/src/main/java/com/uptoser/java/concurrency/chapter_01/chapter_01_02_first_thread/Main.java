package com.uptoser.java.concurrency.chapter_01.chapter_01_02_first_thread;

public class Main {

	/**
	 * 线程的创建和运行
	 */
	public static void main(String[] args) {

		for (int i = 1; i <= 10; ++i) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}

}
