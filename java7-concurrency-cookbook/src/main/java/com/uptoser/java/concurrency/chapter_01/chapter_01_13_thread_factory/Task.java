package com.uptoser.java.concurrency.chapter_01.chapter_01_13_thread_factory;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
