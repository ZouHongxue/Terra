package jmm;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 && 重入锁
 * @Author: coffee
 */
public class LockTest {
    static ReadWriteLock lock = new ReentrantReadWriteLock();
    static Integer count = 0;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20, 100, 5, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(40),
                new MyThreadFactory("myTestPool-"),
                new ThreadPoolExecutor.AbortPolicy());
        long startTime = System.currentTimeMillis();
        readWriteLock(executor);
//        reentrantLock(executor);

        while (true) {
            if (executor.isTerminated()) {
                long endTime = System.currentTimeMillis();
                System.out.println("Time Usage is " + (endTime - startTime) / 1000);
                break;
            }
        }
    }

    private static void readWriteLock(ThreadPoolExecutor executor) {
        for (int i = 0; i < 10; i++) {
            //write process
            DoThings doThings = new DoThings();
            System.out.println("apply task  " + i + " doThings is " + doThings);
            executor.submit(doThings);
        }

        for (int i = 0; i < 20; i++) {
            ReadData readData = new ReadData();
            executor.submit(readData);
        }

        executor.shutdown();
    }

    private static void reentrantLock(ThreadPoolExecutor executor) {
        for (int i = 0; i < 40; i++) {
            DoThings doThings = new DoThings();
            System.out.println("apply task  " + i + " doThings is " + doThings);
            executor.submit(doThings);
        }
        executor.shutdown();
    }

    static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private ThreadGroup threadGroup;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            threadGroup = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        MyThreadFactory(String prefix) {
            SecurityManager s = System.getSecurityManager();
            threadGroup = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = prefix +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(threadGroup, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
