package com.uptoser.java.concurrency.chapter_05_fork_join.chapter_05_05_cancel_task;

import java.util.Random;

/**
 * Class that generates an array of integer numbers between 0 and 10 with a size
 * specified as parameter
 *
 */
public class ArrayGenerator {

	/**
	 * Method that generates an array of integer numbers between 0 and 10 with
	 * the specified size
	 * 
	 * @param size
	 *            The size of the array
	 * @return An array of random integer numbers between 0 and 10
	 */
	public int[] generateArray(int size) {
		int array[] = new int[size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(10);
		}
		return array;
	}

}
