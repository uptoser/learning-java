package com.uptoser.java_learning.concurrency.chapter_03_sync_assistClass.chapter_03_03_count_down_latch;

import java.util.concurrent.CountDownLatch;

/**
 * This class implements the controller of the Videoconference
 * 
 * It uses a CountDownLatch to control the arrival of all the participants in
 * the conference.
 *
 */
public class Videoconference implements Runnable {

	/**
	 * This class uses a CountDownLatch to control the arrivel of all the
	 * participants
	 * CountDownLatch类是一个同步辅助类。在完成一组正在其他线程中执行的操作之前，他允许线程一直等待。
	 * CountDownLatch机制不是用来保护共享资源或者临界区的，它是用来同步执行多个任务的一个或者多个线程
	 * 内部计数器一旦初始化就无法在修改
	 */
	private final CountDownLatch controller;

	/**
	 * Constructor of the class. Initializes the CountDownLatch
	 * 
	 * @param number
	 *            The number of participants in the videoconference
	 */
	public Videoconference(int number) {
		//CountDownLatch类使用一个整数进行初始化，这个整数就是线程要等待完成的操作的数目,内部计数器一旦初始化就无法在修改
		controller = new CountDownLatch(number);
	}

	/**
	 * This method is called by every participant when he incorporates to the
	 * VideoConference
	 * 
	 * @param participant
	 */
	public void arrive(String name) {
		System.out.printf("%s has arrived.\n", name);
		// This method uses the countDown method to decrement the internal
		// countDown会使CountDownLatch类的内部计数器减1.当计数器变成0的时候，CountDownLatch将唤醒所有调用await()的方法(被等待的事件完成的时候调用)
		controller.countDown();
		System.out.printf("VideoConference: Waiting for %d participants.\n", controller.getCount());
	}

	/**
	 * This is the main method of the Controller of the VideoConference. It
	 * waits for all the participants and the, starts the conference
	 */
	@Override
	public void run() {
		System.out.printf("VideoConference: Initialization: %d participants.\n", controller.getCount());
		try {
			// Wait for all the participants
			// 等待CountDownLatch的计数器变成0（需要在等待其他事件先完成的线程调用）
			controller.await();
			// Starts the conference
			System.out.printf("VideoConference: All the participants have come\n");
			System.out.printf("VideoConference: Let's start...\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
