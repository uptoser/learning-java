package com.uptoser.java.concurrency.chapter_01.chapter_01_03_thread_info;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

public class Main {
	/**
	 * 线程信息的获取和设置
	 */
	public static void main(String[] args) throws IOException {
		Thread threads[] = new Thread[10];
		State status[] = new State[10];

		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new Calculator(i));
			if ((i % 2) == 0) {
				threads[i].setPriority(Thread.MAX_PRIORITY);
			} else {
				threads[i].setPriority(Thread.MIN_PRIORITY);
			}
			threads[i].setName("Thread " + i);
		}

		try (FileWriter file = new FileWriter("D:/log.txt"); PrintWriter pw = new PrintWriter(file);) {
			for (int i = 0; i < 10; i++) {
				pw.println("Main : Status of thread" + i + " : " + threads[i].getState());
				status[i] = threads[i].getState();
			}
			for (int i = 0; i < 10; ++i) {
				threads[i].start();
			}

			boolean finish = false;
			while (!finish) {
				for (int i = 0; i < 10; i++) {
					if (threads[i].getState() != status[i]) {
						writeThreadInfo(pw, threads[i], status[i]);
						status[i] = threads[i].getState();
					}
				}
				finish = true;
				for (int i = 0; i < 10; i++) {
					finish = finish && (threads[i].getState() == State.TERMINATED);
				}
			}
		}
	}

	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
		/*
		* Thread类有一些保存信息的属性
		* ID:保存了线程的唯一标识符
		* Name:保存了线程的名称
		* Priority:保存了线程对象的优先级。从1-10，其中10为最高优先级。我们并不推荐去改变线程的优先级
		* Status:保存了线程的状态。线程的状态有6种：new、runnabel、blocked、waiting、time waiting、terminated
		 */
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : Old State: %s\n", state);
		pw.printf("Main : New State: %s\n", thread.getState());
		pw.printf("Main : *************************************\n");
	}

}
