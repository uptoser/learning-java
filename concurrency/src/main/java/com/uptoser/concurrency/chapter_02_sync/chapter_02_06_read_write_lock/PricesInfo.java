package com.uptoser.concurrency.chapter_02_sync.chapter_02_06_read_write_lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This class simulates the store of two prices. We will have a writer that
 * stores the prices and readers that consult this prices
 *
 */
public class PricesInfo {

	/**
	 * The two prices
	 */
	private double price1;
	private double price2;

	/**
	 * Lock to control the access to the prices
	 * 声明锁对象(ReadWriteLock锁接口)
	 */
	private ReadWriteLock lock;

	/**
	 * Constructor of the class. Initializes the prices and the Lock
	 */
	public PricesInfo() {
		price1 = 1.0;
		price2 = 2.0;
		//ReentrantReadWriteLock类是ReadWriteLock锁接口的唯一实现。这个类有两个锁————读操作锁和写操作锁
		lock = new ReentrantReadWriteLock();
	}

	/**
	 * Returns the first price
	 * 
	 * @return the first price
	 */
	public double getPrice1() {
		//获取读锁
		lock.readLock().lock();
		double value = price1;
		//释放读锁
		lock.readLock().unlock();
		return value;
	}

	/**
	 * Returns the second price
	 * 
	 * @return the second price
	 */
	public double getPrice2() {
		lock.readLock().lock();
		double value = price2;
		lock.readLock().unlock();
		return value;
	}

	/**
	 * Establish the prices
	 * 
	 * @param price1
	 *            The price of the first product
	 * @param price2
	 *            The price of the second product
	 */
	public void setPrices(double price1, double price2) {
		//获取写锁
		lock.writeLock().lock();
		this.price1 = price1;
		this.price2 = price2;
		//释放写锁
		lock.writeLock().unlock();
	}
}
