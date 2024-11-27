package com.uptoser.concurrency.chapter_01.chapter_01_09_thread_uncaugt_exception;

/**
 * Runnable class than throws and Exception
 *
 */
public class Task implements Runnable {

	/**
	 * Main method of the class
	 */
	@Override
	public void run() {
		// The next instruction always throws and exception
		int numero = Integer.parseInt("TTT");
	}

}
