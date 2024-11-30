package com.uptoser.java.concurrency.chapter_02_sync.chapter_02_02_synchronized_solution;

/**
 * Main class of the example. It creates an account, a company and a bank to
 * work with the account. The final balance is equal to the initial.
 * 增加了同步关键字声明的方案
 *
 * 每一个用synchronized关键字声明的方法都是临界区（Critical Section）
 *
 * 用synchronized关键字声明的静态方法，同时只能被一个线程访问，但是其他线程可以访问这个对象的非静态方法，
 * 如果静态方法和非静态方法同时改变了相同的数据，将会出现数据不一致的错误
 *
 * synchronized关键字会降低程序的性能，同一个synchronized方法只有一个线程可以访问，其他线程将等待。
 * 我们可以通过synchronized关键字来保护代码块，以获取更好的性能。临界区的访问应该尽可能的短
 * synchronized (this) {
 *     // java code
 * }
 *
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
			System.out.printf("Account : Final Balance: %f\n", account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
