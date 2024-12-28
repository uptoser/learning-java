package com.uptoser.java.concurrency.chapter_01.chapter_01_04_interrupt;

public class Main {
	/**
	 * 线程的中断
	 */
	public static void main(String[] args) {
		Thread task = new PrimeGenerator();
		task.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//等待5秒后线程中断
		task.interrupt();
	}

}
