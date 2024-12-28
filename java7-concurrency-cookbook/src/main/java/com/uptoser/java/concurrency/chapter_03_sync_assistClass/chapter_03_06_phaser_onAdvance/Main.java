package com.uptoser.java.concurrency.chapter_03_sync_assistClass.chapter_03_06_phaser_onAdvance;

/**
 * Main class of the example
 * 并发阶段任务中的阶段切换
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates the Phaser
		MyPhaser phaser = new MyPhaser();

		// Creates 5 students and register them in the phaser
		Student students[] = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = new Student(phaser);
			phaser.register();
		}

		// Create 5 threads for the students and start them
		Thread threads[] = new Thread[students.length];
		for (int i = 0; i < students.length; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}

		// Wait for the finalization of the threads
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Check that the Phaser is in the Terminated state
		System.out.printf("Main: The phaser has finished: %s.\n", phaser.isTerminated());

	}

}
