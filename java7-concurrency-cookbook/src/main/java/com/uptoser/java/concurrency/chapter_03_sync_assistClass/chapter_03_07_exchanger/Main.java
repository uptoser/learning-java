package com.uptoser.java.concurrency.chapter_03_sync_assistClass.chapter_03_07_exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Main class of the example
 * 并发任务间的数据交换
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Creates two buffers
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();

		// Creates the exchanger
		Exchanger<List<String>> exchanger = new Exchanger<>();

		// Creates the producer
		Producer producer = new Producer(buffer1, exchanger);
		// Creates the consumer
		Consumer consumer = new Consumer(buffer2, exchanger);

		// Creates and starts the threads
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);

		threadProducer.start();
		threadConsumer.start();

	}

}
