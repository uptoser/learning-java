package com.uptoser.java.concurrency.chapter_03_sync_assistClass.chapter_03_02_semaphore_many;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a PrintQueue that have access to three printers.
 * 
 * We use a Semaphore to control the access to one of the printers. When a job
 * wants to print, if there is one or more printers free, it has access to one
 * of the free printers. If not, it sleeps until one of the printers is free.
 *
 */
public class PrintQueue {

	/**
	 * Semaphore to control the access to the printers
	 */
	private Semaphore semaphore;

	/**
	 * Array to control what printer is free
	 */
	private boolean freePrinters[];

	/**
	 * Lock to control the access to the freePrinters array
	 */
	private Lock lockPrinters;

	/**
	 * Constructor of the class. It initializes the three objects
	 */
	public PrintQueue() {
		semaphore = new Semaphore(3);
		freePrinters = new boolean[3];
		for (int i = 0; i < 3; i++) {
			freePrinters[i] = true;
		}
		lockPrinters = new ReentrantLock();
	}

	public void printJob(Object document) {
		try {
			// Get access to the semaphore. If there is one or more printers
			// free,
			// it will get the access to one of the printers
			//初始化信号量对象，传入参数1代表创建二进制信号量，所以它只能保护一个共享资源的访问。
			//如果传入第二个参数(布尔类型)代表是否为公平模式
			// 获取信号量还有其他两个方法 1.tryAcquire()试图获取信号量 2.acquireUninterruptibly()忽略中断的
			semaphore.acquire();

			// Get the number of the free printer
			int assignedPrinter = getPrinter();

			Long duration = (long) (Math.random() * 10);
			System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",
					Thread.currentThread().getName(), assignedPrinter, duration);
			TimeUnit.SECONDS.sleep(duration);

			// Free the printer
			freePrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// Free the semaphore
			semaphore.release();
		}
	}

	private int getPrinter() {
		int ret = -1;

		try {
			// Get the access to the array
			lockPrinters.lock();
			// Look for the first free printer
			for (int i = 0; i < freePrinters.length; i++) {
				if (freePrinters[i]) {
					ret = i;
					freePrinters[i] = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Free the access to the array
			lockPrinters.unlock();
		}
		return ret;
	}

}
