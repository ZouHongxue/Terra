package jmm;

/**
 * @Author: coffee
 */
class NotifyThread extends Thread {
    @Override
    public void run() {
        synchronized (this) {
            System.out.println("等待...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}