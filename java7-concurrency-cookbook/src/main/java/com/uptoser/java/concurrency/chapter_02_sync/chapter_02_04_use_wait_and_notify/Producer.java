package com.uptoser.java.concurrency.chapter_02_sync.chapter_02_04_use_wait_and_notify;

/**
 * This class implements a producer of events.
 *
 */
public class Producer implements Runnable {

	/**
	 * Store to work with
	 */
	private EventStorage storage;

	/**
	 * Constructor of the class. Initialize the storage.
	 * 
	 * @param storage
	 *            The store to work with
	 */
	public Producer(EventStorage storage) {
		this.storage = storage;
	}

	/**
	 * Core method of the producer. Generates 100 events.
	 */
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}
}
