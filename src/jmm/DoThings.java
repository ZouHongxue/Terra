package jmm;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: coffee
 */
class DoThings implements Callable<Boolean> {
    private Lock getLock() {
        return LockTest.lock.writeLock();
    }

    private boolean doSomething(Lock lock) {
        if (lock.tryLock()) {
            try {
                System.out.println(++LockTest.count + " object is " + this);
                System.out.println("who get lock " + Thread.currentThread().getName());
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("who release lock " + Thread.currentThread().getName());
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean call() {
        while (true) {
            boolean res = doSomething(getLock());
//            System.out.println("object is " + this + " exec " + res);
            if (res) {
                return true;
            }
        }
    }
}