package com.uptoser.concurrency.chapter_01.chapter_01_04;

public class Main {

	public static void main(String[] args) {
		Thread task = new PrimeGenerator();
		task.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		task.interrupt();
	}

}
