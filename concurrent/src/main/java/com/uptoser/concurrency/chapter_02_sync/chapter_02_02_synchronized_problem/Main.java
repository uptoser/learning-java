package com.uptoser.concurrency.chapter_02_sync.chapter_02_02_synchronized_problem;

/**
 * Main class of the example. It creates an account, a company and a bank to
 * work with the account. The final balance should be equal to the initial,
 * but....
 * 未增加同步关键字声明的方案
 */
public class Main {

	/**
	 * Main method of the example
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Creates a new account ...
		Account account = new Account();
		// an initialize its balance to 1000
		account.setBalance(1000);

		// Creates a new Company and a Thread to run its task
		Company company = new Company(account);
		Thread companyThread = new Thread(company);
		// Creates a new Bank and a Thread to run its task
		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);

		// Prints the initial balance
		System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

		// Starts the Threads
		companyThread.start();
		bankThread.start();

		try {
			// Wait for the finalization of the Threads
			companyThread.join();
			bankThread.join();
			// Print the final balance
			// 正常的结果应该是1000，由于没有加同步关键字，导致数据问题
			System.out.printf("Account : Final Balance: %f\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
