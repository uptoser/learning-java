package com.uptoser.java.javase.other.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Java 5新增了一个Executors工厂类来产生线程池，该工厂类包含如下几个静态工厂方法来创建线程池。
 * ➢ newCachedThreadPool()：创建一个具有缓存功能的线程池，系统根据需要创建线程，这些线程将会被缓存在线程池中。
 * ➢ newFixedThreadPool(int nThreads)：创建一个可重用的、具有固定线程数的线程池。
 * ➢ newSingleThreadExecutor()：创建一个只有单线程的线程池，它相当于调用newFixedThread Pool()方法时传入参数为1。
 * ➢ newScheduledThreadPool(int corePoolSize)：创建具有指定线程数的线程池，它可以在指定延迟后执行线程任务。
 * corePoolSize指池中所保存的线程数，即使线程是空闲的也被保存在线程池内。
 * ➢ newSingleThreadScheduledExecutor()：创建只有一个线程的线程池，它可以在指定延迟后执行线程任务。
 * ➢ ExecutorService newWorkStealingPool(int parallelism)：创建持有足够的线程的线程池来支持给定的并行级别
 * ➢ ExecutorService newWorkStealingPool()：该方法是前一个方法的简化版本。如果当前机器有4个CPU，则目标并行级别被设置为4
 * 上面7个方法中的前三个方法返回一个ExecutorService对象，该对象代表一个线程池，它可以执行Runnable对象或Callable对象所代表的线程；
 * 而中间两个方法返回一个ScheduledExecutorService线程池，它是ExecutorService的子类，它可以在指定延迟后执行线程任务；
 * 最后两个方法则是Java 8新增的，这两个方法可充分利用多CPU并行的能力。
 * 这两个方法生成的work stealing池，都相当于后台线程池，如果所有的前台线程都死亡了，work stealing池中的线程会自动死亡。
 *
 * ExecutorService代表尽快执行线程的线程池（只要线程池中有空闲线程，就立即执行线程任务），
 * 程序只要将一个Runnable对象或Callable对象（代表线程任务）提交给该线程池，该线程池就会尽快执行该任务。
 * ExecutorService里提供了如下三个方法。
 * ➢ Future<?> submit(Runnable task)：将一个Runnable对象提交给指定的线程池，线程池将在有空闲线程时执行Runnable对象代表的任务。
 * 其中Future对象代表Runnable任务的返回值——但run()方法没有返回值，所以Future对象将在run()方法执行结束后返回null。
 * 但可以调用Future的isDone()、isCancelled()方法来获得Runnable对象的执行状态。
 * ➢ <T>Future<T>submit(Runnable task, T result)：将一个Runnable对象提交给指定的线程池，
 * 线程池将在有空闲线程时执行Runnable对象代表的任务。其中result显式指定线程执行结束后的返回值，
 * 所以Future对象将在run()方法执行结束后返回result。
 * ➢ <T>Future<T>submit(Callable<T>task)：将一个Callable对象提交给指定的线程池，
 * 线程池将在有空闲线程时执行Callable对象代表的任务。其中Future代表Callable对象里call()方法的返回值。
 *
 * ScheduledExecutorService代表可在指定延迟后或周期性地执行线程任务的线程池，它提供了如下4个方法。
 * ➢ ScheduledFuture<V>schedule(Callable<V>callable, long delay, TimeUnit unit)：指定callable任务将在delay延迟后执行。
 * ➢ ScheduledFuture<?>schedule(Runnable command, long delay, TimeUnit unit)：指定command任务将在delay延迟后执行。
 * ➢ ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)：
 * 指定command任务将在delay延迟后执行，而且以设定频率重复执行。也就是说，在initialDelay后开始执行，
 * 依次在initialDelay+period、initialDelay+2*period…处重复执行，依此类推。
 * ➢ ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)：
 * 创建并执行一个在给定初始延迟后首次启用的定期操作，随后在每一次执行终止和下一次执行开始之间都存在给定的延迟。
 * 如果任务在任一次执行时遇到异常，就会取消后续执行；否则，只能通过程序来显式取消或终止该任务。
 *
 * 用完一个线程池后，应该调用该线程池的shutdown()方法，该方法将启动线程池的关闭序列，调用shutdown()方法后的线程池不再接收新任务，
 * 但会将以前所有已提交任务执行完成。当线程池中的所有任务都执行完成后，池中的所有线程都会死亡；
 * 另外也可以调用线程池的shutdownNow()方法来关闭线程池，该方法试图停止所有正在执行的活动任务，
 * 暂停处理正在等待的任务，并返回等待执行的任务列表。
 */
public class JavaThreadPoolMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个具有固定线程数（6）的线程池
        ExecutorService pool = Executors.newFixedThreadPool(6);
        // 向线程池中提交两个线程
        Runnable thread = ()->{for(int i=0;i<10;i++) System.out.println(Thread.currentThread().getName()+"-"+i);};
        pool.submit(thread);
        pool.submit(thread);
        // 关闭线程池
        pool.shutdown();

        //-------------------------------------
//        new JavaThreadPoolMain().forkJoin();

    }

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
    public void forkJoin() throws ExecutionException, InterruptedException {
        // 继承RecursiveTask来实现"可分解"的任务
        class CalTask extends RecursiveTask<Integer> {
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
