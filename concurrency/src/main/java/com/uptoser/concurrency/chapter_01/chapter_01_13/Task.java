package com.uptoser.concurrency.chapter_01.chapter_01_13;

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
