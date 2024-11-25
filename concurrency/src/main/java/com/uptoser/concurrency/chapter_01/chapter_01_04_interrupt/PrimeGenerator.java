package com.uptoser.concurrency.chapter_01.chapter_01_04_interrupt;

public class PrimeGenerator extends Thread {

	@Override
	public void run() {
		long number = 1L;
		while (true) {
			if (isPrime(number)) {
				System.out.printf("Number %d is Prime.\n", number);
			}
			//判断线程是否被中断，如果线程调用了interrupt()方法，则isInterrupted()返回为true
			if (isInterrupted()) {
				System.out.printf("The Prime Generator has been Interrupted\n");
				return;
			}
			number++;
		}
	}

	/**
	 * 如果接收到的参数是一个指数则返回true
	 */
	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

}
