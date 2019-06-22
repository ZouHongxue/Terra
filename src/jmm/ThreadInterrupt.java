package jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: coffee
 */
public class ThreadInterrupt {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private static final ThreadInterrupt threadInterrupt = new ThreadInterrupt();

    class InterruptThread extends Thread {
        @Override
        public void run() {
            System.out.println("线程启动...");
            this.interrupt();
            System.out.println(this.isInterrupted());
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptThread thread = threadInterrupt.new InterruptThread();
        thread.setName("test_interrupt");
        thread.start();
        if (thread.isInterrupted()) {
            thread.interrupt();
        }
        threadInterrupt.countDownLatch.await();
        System.out.println(thread.isInterrupted());
        System.out.println("继续执行...");

        NotifyThread notifyThread = new NotifyThread();
        notifyThread.setName("notifyThread");


        synchronized (notifyThread) {
            notifyThread.start();
        }
        System.out.println("唤醒...");
        notifyThread.notifyAll();
    }
}
