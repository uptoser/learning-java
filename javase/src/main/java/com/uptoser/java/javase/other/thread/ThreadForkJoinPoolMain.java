package com.uptoser.java.javase.other.thread;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Java 7提供了ForkJoinPool来支持将一个任务拆分成多个“小任务”并行计算，再把多个“小任务”的结果合并成总的计算结果。
 * ForkJoinPool是ExecutorService的实现类，因此是一种特殊的线程池。ForkJoinPool提供了如下两个常用的构造器。
 * ➢ ForkJoinPool(int parallelism)：创建一个包含parallelism个并行线程的ForkJoinPool。
 * ➢ ForkJoinPool()：以Runtime.availableProcessors()方法的返回值作为parallelism参数来创建Fork JoinPool。
 * Java 8进一步扩展了ForkJoinPool的功能，Java 8为ForkJoinPool增加了通用池功能。ForkJoinPool类通过如下两个静态方法提供通用池功能。
 * ➢ ForkJoinPool commonPool()：该方法返回一个通用池，通用池的运行状态不会受shutdown()或shutdownNow()方法的影响。
 * ➢ int getCommonPoolParallelism()：该方法返回通用池的并行级别。
 *
 * 创建了ForkJoinPool实例之后，就可调用ForkJoinPool的submit(ForkJoinTask task)或invoke(ForkJoinTask task)方法来执行指定任务了
 * 其中ForkJoinTask代表一个可以并行、合并的任务。ForkJoinTask是一个抽象类，它还有两个抽象子类：RecursiveAction和RecursiveTask。
 * 其中RecursiveTask代表有返回值的任务，而RecursiveAction代表没有返回值的任务。
 */
public class ThreadForkJoinPoolMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 继承RecursiveTask来实现"可分解"的任务
        class CalTask extends RecursiveTask<Integer>{
            // 每个“小任务”只最多只累加20个数
            private static final int THRESHOLD = 20;
            private int arr[];
            private int start;
            private int end;
            // 累加从start到end的数组元素
            public CalTask(int[] arr , int start, int end){
                this.arr = arr;
                this.start = start;
                this.end = end;
            }
            @Override
            protected Integer compute(){
                int sum = 0;
                // 当end与start之间的差小于THRESHOLD时，开始进行实际累加
                if(end - start < THRESHOLD){
                    for (int i = start ; i < end ; i++ ){
                        sum += arr[i];
                    }
                    return sum;
                }else{
                    // 如果当end与start之间的差大于THRESHOLD时，即要打印的数超过20个
                    // 将大任务分解成两个小任务。
                    int middle = (start + end) /2;
                    CalTask left = new CalTask(arr , start, middle);
                    CalTask right = new CalTask(arr , middle, end);
                    // 并行执行两个“小任务”
                    left.fork();
                    right.fork();
                    // 把两个“小任务”累加的结果合并起来
                    return left.join() + right.join();
                }
            }
        }

        int[] arr = new int[100];
        Random rand = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0 , len = arr.length; i < len ; i++ ){
            int tmp = rand.nextInt(20);
            // 对数组元素赋值，并将数组元素的值添加到total总和中。
            total += (arr[i] = tmp);
        }
        System.out.println(total);

        ForkJoinPool pool = new ForkJoinPool();
        // 提交可分解的CalTask任务
        Future<Integer> future = pool.submit(new CalTask(arr , 0 , arr.length));
        System.out.println(future.get());
        // 关闭线程池
        pool.shutdown();

    }
}
