package com.uptoser.java_learning.concurrency.chapter_02_sync.chapter_02_04_use_wait_and_notify;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements an Event storage. Producers will storage events in it
 * and Consumers will process them. An event will be a java.util.Date object
 *
 */
public class EventStorage {

	/**
	 * Maximum size of the storage
	 */
	private int maxSize;
	/**
	 * Storage of events
	 */
	private List<Date> storage;

	/**
	 * Constructor of the class. Initializes the attributes.
	 */
	public EventStorage() {
		maxSize = 10;
		storage = new LinkedList<>();
	}

	/**
	 * This method creates and storage an event.
	 */
	public synchronized void set() {
		/*
			当列表中的数据已经满时，使用wait()方法挂起线程
			wait()方法只能在同步代码块中调用，如果在同步代码块之外调用将会抛出IllegalMonitorStateException异常
			必须在while中调用wait()，并且不断查询while的条件，知道条件为真的时候才能继续
		 */
		while (storage.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storage.add(new Date());
		System.out.printf("Set: %d\n", storage.size());
		/*
		  使用notify()或notifyAll()方法唤醒因调用wait()方法进入休眠的线程
		*/
		notify();
	}

	/**
	 * This method delete the first event of the storage.
	 */
	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Get: %d: %s\n", storage.size(), ((LinkedList<?>) storage).poll());
		notify();
	}

}
