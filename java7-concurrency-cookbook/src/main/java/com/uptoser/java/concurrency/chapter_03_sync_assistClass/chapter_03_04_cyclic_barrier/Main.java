package com.uptoser.java.concurrency.chapter_03_sync_assistClass.chapter_03_04_cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Main class of the example
 * 在集合点的同步
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		 * Initializes the bi-dimensional array of data 10000 rows 1000 numbers
		 * in each row Looking for number 5
		 */
		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANT = 2000;
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

		// Initializes the object for the results
		Results results = new Results(ROWS);

		// Creates an Grouper object
		Grouper grouper = new Grouper(results);

		// Creates the CyclicBarrier object. It has 5 participants and, when
		// they finish, the CyclicBarrier will execute the grouper object
		/*
			CyclicBarrier使用整型进行初始化，这个数就是需要在某个点上同步的线程数
			CyclicBarrier类可以传入一个Runnable对象初始化参数。当所有线程都到达集合点后，将执行该线程(有点类似于分治编程技术)

			CyclicBarrier类除了await()方法还提供了getNumberWaiting()方法和getParties()方法，
			前者返回await()上阻塞的线程数目，后者返回被CyclicBarrier对象同步的任务数。

			CyclicBarrier对象可以重置，通过reset()方法完成。当重置发生后，await()方法中等待的线程将收到一个BrokenBarrierException异常

			CyclicBarrier对象有损坏状态，通过isBroken()来判断，true就是损坏状态。
			当很多线程在等待的时候，如果其中一个线程被中断。这个线程会抛出InterruptedException异常，
			其他线程会抛出BrokenBarrierException异常，这个时候CyclicBarrier就处于损坏状态了。
		 */
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);

		// Creates, initializes and starts 5 Searcher objects
		Searcher searchers[] = new Searcher[PARTICIPANTS];
		for (int i = 0; i < PARTICIPANTS; i++) {
			searchers[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock,
					results, 5, barrier);
			Thread thread = new Thread(searchers[i]);
			thread.start();
		}
		System.out.printf("Main: The main thread has finished.\n");
	}

}
