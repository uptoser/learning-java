package com.uptoser.java.concurrency.chapter_02_sync.chapter_02_07_update_lock_fair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class simulates a print queue.
 *
 */
public class PrintQueue {

	/**
	 * Creates a lock to control the access to the queue. With the boolean
	 * attribute, we control the fairness of the Lock
	 * 创建一个锁对象，构造器中的参数为fair（默认为false，它成为非公平模式。如果fair为true，成为公平模式。选取等待时间最长的）
	 * tryLock()不收fair属性的影响。因为tryLock()是立即返回的。
	 */
	private final Lock queueLock = new ReentrantLock(true);

	/**
	 * Method that prints the Job. The printing is divided in two phase two show
	 * how the fairness attribute affects the election of the thread who has the
	 * control of the lock
	 * 
	 * @param document
	 *            The document to print
	 */
	public void printJob(Object document) {
		queueLock.lock();

		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(),
					(duration / 1000));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}

		queueLock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(),
					(duration / 1000));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			queueLock.unlock();
		}
	}
}
