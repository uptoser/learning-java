package com.uptoser.java.concurrency.chapter_02_sync.chapter_02_08_use_multi_condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a buffer to stores the simulate file lines between the
 * producer and the consumers
 * 
 */
public class Buffer {

	/**
	 * The buffer
	 * 用来存放共享数据
	 */
	private LinkedList<String> buffer;

	/**
	 * Size of the buffer
	 * buffer的长度
	 */
	private int maxSize;

	/**
	 * Lock to control the access to the buffer
	 */
	private ReentrantLock lock;

	/**
	 * Conditions to control that the buffer has lines and has empty space
	 */
	private Condition lines;
	private Condition space;

	/**
	 * Attribute to control where are pending lines in the buffer
	 * 用来表明缓冲区中是否还有数据
	 */
	private boolean pendingLines;

	/**
	 * Constructor of the class. Initialize all the objects
	 * 
	 * @param maxSize
	 *            The size of the buffer
	 */
	public Buffer(int maxSize) {
		this.maxSize = maxSize;
		buffer = new LinkedList<>();
		lock = new ReentrantLock();
		//通过Lock接口声明的newCondition()方法创建条件
		lines = lock.newCondition();
		space = lock.newCondition();
		pendingLines = true;
	}

	/**
	 * Insert a line in the buffer
	 * 
	 * @param line
	 *            line to insert in the buffer
	 */
	public void insert(String line) {
		lock.lock();
		try {
			while (buffer.size() == maxSize) {
				/*
				  await(Long time,TimeUnit unit)方法可以添加时间，等待时间过去后将退出休眠。
				  awaitUninterruptibly()，它是不可中断的。
				  awaitUntil(Date date),指定最后的截止日期
				 */
				//如果缓冲区满了则等待space(释放当前的锁)。
				space.await();
			}
			buffer.offer(line);
			System.out.printf("%s: Inserted Line: %d\n", Thread.currentThread().getName(), buffer.size());
			//唤醒其他lines的线程
			lines.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Returns a line from the buffer
	 * 
	 * @return a line from the buffer
	 */
	public String get() {
		String line = null;
		lock.lock();
		try {
			while ((buffer.size() == 0) && (hasPendingLines())) {
				lines.await();
			}

			if (hasPendingLines()) {
				line = buffer.poll();
				System.out.printf("%s: Line Readed: %d\n", Thread.currentThread().getName(), buffer.size());
				space.signalAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return line;
	}

	/**
	 * Establish the value of the variable
	 * 
	 * @param pendingLines
	 */
	public void setPendingLines(boolean pendingLines) {
		this.pendingLines = pendingLines;
	}

	/**
	 * Returns the value of the variable
	 * 
	 * @return the value of the variable
	 */
	public boolean hasPendingLines() {
		return pendingLines || buffer.size() > 0;
	}

}
