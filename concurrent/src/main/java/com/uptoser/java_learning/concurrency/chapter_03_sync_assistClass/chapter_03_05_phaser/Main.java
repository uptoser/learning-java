package com.uptoser.java_learning.concurrency.chapter_03_sync_assistClass.chapter_03_05_phaser;

import java.util.concurrent.Phaser;

/**
 * Main class of the example
 * 并发 分阶段任务的运行
 * Phaser类
 * arriveAndAwaitAdvance()当调用该方法时，Phaser对象将减1
 * arriveAndDeregister()方法代表这个现成已经完成了当前语句，并且不会再下一个阶段中参与。
 * isTerminated()判断Phaser是否终止
 * onAdvance()方法返回true就代表终止态的时候。Phaser是终止态的时候arriveAndAwaitAdvance()会立即返回，不会做任何同步的操作
 *
 * arrive()
 * awaitAdvance(int phase)
 *
 * register()将增加一个新的参与者，注册到Phaser中
 * bulkRegister(int Parties)
 *
 * forceTermination()强制终止Phaser
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates a Phaser with three participants
		// 参数为参与阶段同步的线程的个数
		Phaser phaser = new Phaser(3);

		// Creates 3 FileSearch objects. Each of them search in different
		// directory
		FileSearch system = new FileSearch("C:\\Windows", "txt", phaser);
		FileSearch apps = new FileSearch("C:\\Program Files", "txt", phaser);
		FileSearch documents = new FileSearch("C:\\Documents And Settings", "txt", phaser);

		// Creates a thread to run the system FileSearch and starts it
		Thread systemThread = new Thread(system, "System");
		systemThread.start();

		// Creates a thread to run the apps FileSearch and starts it
		Thread appsThread = new Thread(apps, "Apps");
		appsThread.start();

		// Creates a thread to run the documents FileSearch and starts it
		Thread documentsThread = new Thread(documents, "Documents");
		documentsThread.start();
		try {
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.printf("Terminated: %s\n", phaser.isTerminated());

	}

}
