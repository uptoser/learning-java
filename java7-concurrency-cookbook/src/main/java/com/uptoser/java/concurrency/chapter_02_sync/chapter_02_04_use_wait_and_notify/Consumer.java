package com.uptoser.java.concurrency.chapter_02_sync.chapter_02_04_use_wait_and_notify;

/**
 * This class implements a consumer of events.
 *
 */
public class Consumer implements Runnable {

	/**
	 * Store to work with
	 */
	private EventStorage storage;

	/**
	 * Constructor of the class. Initialize the storage
	 * 
	 * @param storage
	 *            The store to work with
	 */
	public Consumer(EventStorage storage) {
		this.storage = storage;
	}

	/**
	 * Core method for the consumer. Consume 100 events
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.get();
		}
	}

}
