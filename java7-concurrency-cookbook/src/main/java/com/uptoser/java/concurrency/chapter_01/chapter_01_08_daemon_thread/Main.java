package com.uptoser.java.concurrency.chapter_01.chapter_01_08_daemon_thread;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Main class of the example. Creates three WriterTaks and a CleanerTask
 * 守护线程的创建和运行
 */
public class Main {

	/**
	 * Main method of the example. Creates three WriterTasks and a CleanerTask
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates the Event data structure
		Deque<Event> deque = new ArrayDeque<Event>();

		// Creates the three WriterTask and starts them
		WriterTask writer = new WriterTask(deque);
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(writer);
			thread.start();
		}

		// Creates a cleaner task and starts them
		CleanerTask cleaner = new CleanerTask(deque);
		cleaner.start();

	}

}
