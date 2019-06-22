package jmm;

import java.util.concurrent.Callable;

/**
 * @Author: coffee
 */
public class ReadData implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        while (true) {
            if (LockTest.lock.readLock().tryLock()) {
                try {
                    System.out.println("who get lock " + Thread.currentThread().getName());
                    System.out.println("the count data is " + LockTest.count);
                    Thread.sleep(500);
                    return LockTest.count;
                } finally {
                    LockTest.lock.readLock().unlock();
                    System.out.println("who release lock " + Thread.currentThread().getName());
                }
            }
        }
    }
}
