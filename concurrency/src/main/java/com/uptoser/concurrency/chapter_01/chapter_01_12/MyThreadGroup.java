package com.uptoser.concurrency.chapter_01.chapter_01_12;

public class MyThreadGroup extends ThreadGroup {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("The thread %s has thrown an Exception \n", t.getName());
		e.printStackTrace(System.out);
		System.out.println("Terminating the rest of the Threads\n");
		interrupt();
	}

	public MyThreadGroup(String name) {
		super(name);
	}

}
