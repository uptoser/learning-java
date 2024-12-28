package com.uptoser.java.concurrency.chapter_04_executor.chapter_04_04_getFirstResult_invokeAny;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the main class of the example. Creates two user validation systems
 * and execute them in an Executor using the invokeAny() method. If the user is
 * validated by one of the user validation systems, then it shows a message. If
 * both system don't validate the user, the application proccess the
 * ExecutionException throwed by the method
 * 运行多个任务并处理第一个结果
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Initialize the parameters of the user
		String username = "test";
		String password = "test";

		// Create two user validation objects
		UserValidator ldapValidator = new UserValidator("LDAP");
		UserValidator dbValidator = new UserValidator("DataBase");

		// Create two tasks for the user validation objects
		TaskValidator ldapTask = new TaskValidator(ldapValidator, username, password);
		TaskValidator dbTask = new TaskValidator(dbValidator, username, password);

		// Add the two tasks to a list of tasks
		List<TaskValidator> taskList = new ArrayList<>();
		taskList.add(ldapTask);
		taskList.add(dbTask);

		// Create a new Executor
		ExecutorService executor = (ExecutorService) Executors.newCachedThreadPool();
		String result;
		try {
			// Send the list of tasks to the executor and waits for the result
			// of the first task
			// that finish without throw and Exception. If all the tasks throw
			// and Exception, the
			// method throws and ExecutionException.
			//invokeAny()方法的返回值与call里的相同
			result = executor.invokeAny(taskList);
			System.out.printf("Main: Result: %s\n", result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		// Shutdown the Executor
		executor.shutdown();
		System.out.printf("Main: End of the Execution\n");
	}

}
