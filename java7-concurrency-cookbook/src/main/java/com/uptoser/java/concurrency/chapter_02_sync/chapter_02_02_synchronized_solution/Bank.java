package com.uptoser.java.concurrency.chapter_02_sync.chapter_02_02_synchronized_solution;

/**
 * This class simulates a bank or a cash dispenser that takes money from an
 * account
 * 
 */
public class Bank implements Runnable {

	/**
	 * The account affected by the operations
	 */
	private Account account;

	/**
	 * Constructor of the class. Initializes the account
	 * 
	 * @param account
	 *            The account affected by the operations
	 */
	public Bank(Account account) {
		this.account = account;
	}

	/**
	 * Core method of the Runnable
	 */
	public void run() {
		for (int i = 0; i < 100; i++) {
			account.subtractAmount(1000);
		}
	}

}
