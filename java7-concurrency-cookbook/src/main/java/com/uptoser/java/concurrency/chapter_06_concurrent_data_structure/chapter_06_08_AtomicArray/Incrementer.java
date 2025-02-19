package com.uptoser.java.concurrency.chapter_06_concurrent_data_structure.chapter_06_08_AtomicArray;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * This task implements an incrementer. It increments by 1 all the elements of
 * an array
 *
 */
public class Incrementer implements Runnable {

	/**
	 * Array that store the elements to increment
	 */
	private AtomicIntegerArray vector;

	/**
	 * Constructor of the class
	 * 
	 * @param vector
	 *            Array to store the elements to increment
	 */
	public Incrementer(AtomicIntegerArray vector) {
		this.vector = vector;
	}

	/**
	 * Main method of the task. Increment all the elements of the array
	 */
	@Override
	public void run() {

		for (int i = 0; i < vector.length(); i++) {
			vector.getAndIncrement(i);
		}

	}

}
