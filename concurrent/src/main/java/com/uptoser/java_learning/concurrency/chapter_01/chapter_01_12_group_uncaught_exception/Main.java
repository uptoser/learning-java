package com.uptoser.java_learning.concurrency.chapter_01.chapter_01_12_group_uncaught_exception;

/**
 * 线程组中不可控的异常
 */
public class Main {

	public static void main(String[] args) {
		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
		Task task = new Task();
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(threadGroup, task);
			t.setName("Thread" + i);
			t.start();
		}
	}

}
