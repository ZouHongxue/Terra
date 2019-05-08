package jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zouhongxue
 * @Date: 2019/4/17 11:21 PM
 */
class MyData {
    volatile int data = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    void addTo60() {
        this.data = 60;
    }

    void addOne() {
        data++;
    }

    void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * @Author: zouhongxue
 * @Date: 2019/4/17 11:21 PM
 */
public class VolatileDemo {
    public static void main(String[] args) {
        visibleTestAboutVolatile();
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addOne();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()
                + "\t final value is:" + myData.data);
        System.out.println(Thread.currentThread().getName()
                + "\t final value is:" + myData.atomicInteger);
        String abc = new String("abc");
        changeString(abc);
        System.out.println(abc);
    }

    private static void changeString(String abc) {
        abc = "def";
    }

    private static void visibleTestAboutVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t update number value:" + myData.data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        while (myData.data == 0) {

        }

        System.out.println(Thread.currentThread().getName() + "\t mission success");
    }


}
