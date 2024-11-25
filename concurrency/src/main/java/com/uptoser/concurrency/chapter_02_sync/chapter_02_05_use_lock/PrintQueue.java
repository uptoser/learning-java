package com.uptoser.concurrency.chapter_02_sync.chapter_02_05_use_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class simulates a print queue
 *
 */
public class PrintQueue {

	/**
	 * Lock to control the access to the queue.
	 * 声明一个锁对象，并且用ReentrantLock(Lock接口的一个实现)类初始化
	 */
	private final Lock queueLock = new ReentrantLock();

	/**
	 * Method that prints a document
	 * 
	 * @param document
	 *            document to print
	 */
	public void printJob(Object document) {
		/*
			获取锁。
			如果当前锁正在其他线程中，则当前线程休眠直到前一个线程执行完临界区的代码

			编程人员应该重视tryLock()方法。该方法如果返回true则代表获取了锁，如果返回为false代表没有获取锁，tryLock()将立即返回，它不会将线程置入休眠。
		 */
		queueLock.lock();

		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(),
					(duration / 1000));
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//释放锁。当一个线程离开临界区的时候，我们必须用这个方法来释放锁。如果使用了try-catch模块，不要忘记在finally加入unlock()
			queueLock.unlock();
		}
	}
}
