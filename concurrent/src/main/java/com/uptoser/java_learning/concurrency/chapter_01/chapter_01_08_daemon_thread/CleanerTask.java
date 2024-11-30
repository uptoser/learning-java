package com.uptoser.java_learning.concurrency.chapter_01.chapter_01_08_daemon_thread;

import java.util.Date;
import java.util.Deque;

/**
 * Class that review the Event data structure and delete the events older than
 * ten seconds
 *
 */
public class CleanerTask extends Thread {

	/**
	 * Data structure that stores events
	 */
	private Deque<Event> deque;

	/**
	 * Constructor of the class
	 * 
	 * @param deque
	 *            data structure that stores events
	 */
	public CleanerTask(Deque<Event> deque) {
		this.deque = deque;
		// Establish that this is a Daemon Thread
		// 这个方法只能在start()方法被调用之前设置，线程开始运行就不能修改。isDaemon()方法被用来检查是否是一个守护线程
		setDaemon(true);
	}

	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		while (true) {
			Date date = new Date();
			clean(date);
		}
	}

	/**
	 * Method that review the Events data structure and delete the events older
	 * than ten seconds
	 * 
	 * @param date
	 */
	private void clean(Date date) {
		long difference;
		boolean delete;
		if (deque.size() == 0) {
			return;
		}
		delete = false;
		do {
			Event e = deque.getLast();
			difference = date.getTime() - e.getDate().getTime();
			if (difference > 10000) {
				System.out.printf("Cleaner: %s\n", e.getEvent());
				deque.removeLast();
				delete = true;
			}
		} while (difference > 10000);
		if (delete) {
			System.out.printf("Cleaner: Size of the queue: %d\n", deque.size());
		}
	}
}
