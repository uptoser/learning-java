package com.uptoser.concurrency.chapter_01.chapter_01_02;

public class Main {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; ++i) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}

}
