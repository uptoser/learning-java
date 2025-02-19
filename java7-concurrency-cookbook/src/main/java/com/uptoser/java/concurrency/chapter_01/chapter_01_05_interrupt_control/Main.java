package com.uptoser.java.concurrency.chapter_01.chapter_01_05_interrupt_control;

import java.util.concurrent.TimeUnit;

/**
 * Main class of the example. Search for the autoexect.bat file on the Windows
 * root folder and its subfolders during ten seconds and then, interrupts the
 * Thread
 * 线程中断的控制
 */
public class Main {

	/**
	 * Main method of the core. Search for the autoexect.bat file on the Windows
	 * root folder and its subfolders during ten seconds and then, interrupts
	 * the Thread
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates the Runnable object and the Thread to run it
		FileSearch searcher = new FileSearch("C:\\", "autoexec.bat");
		Thread thread = new Thread(searcher);

		// Starts the Thread
		thread.start();

		// Wait for ten seconds
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Interrupts the thread
		thread.interrupt();
	}

}
