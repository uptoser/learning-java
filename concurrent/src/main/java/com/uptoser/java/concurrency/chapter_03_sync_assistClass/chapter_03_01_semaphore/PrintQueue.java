package com.uptoser.java.concurrency.chapter_03_sync_assistClass.chapter_03_01_semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * This class implements the PrintQueue using a Semaphore to control the access
 * to it.
 *
 */
public class PrintQueue {

	/**
	 * Semaphore to control the access to the queue
	 */
	private final Semaphore semaphore;

	/**
	 * Constructor of the class. Initializes the semaphore
	 */
	public PrintQueue() {
		//初始化信号量对象，传入参数1代表创建二进制信号量，所以它只能保护一个共享资源的访问。
		//如果传入第二个参数(布尔类型)代表是否为公平模式
		// 获取信号量还有其他两个方法 1.tryAcquire()试图获取信号量 2.acquireUninterruptibly()忽略中断的
		semaphore = new Semaphore(1);
	}

	/**
	 * Method that simulates printing a document
	 * 
	 * @param document
	 *            Document to print
	 */
	public void printJob(Object document) {
		try {
			// Get the access to the semaphore. If other job is printing, this
			// thread sleep until get the access to the semaphore
			semaphore.acquire();

			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(),
					duration);
			Thread.sleep(duration);
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// Free the semaphore. If there are other threads waiting for this
			// semaphore,
			// the JVM selects one of this threads and give it the access.
			semaphore.release();
		}
	}

}
