package com.uptoser.java.concurrency.chapter_01.chapter_01_09_thread_uncaugt_exception;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Class that process the uncaught exceptions throwed in a Thread
 * 实现用来处理运行时异常的类。这个类实现UncaughtExceptionHandler接口，并实现uncaughtException()方法
 */
public class ExceptionHandler implements UncaughtExceptionHandler {

	/**
	 * Main method of the class. It process the uncaught excpetions throwed in a
	 * Thread
	 * 
	 * @param t
	 *            The Thead than throws the Exception
	 * @param e
	 *            The Exception throwed
	 */
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());
	}

}
