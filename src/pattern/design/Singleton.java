package pattern.design;

/**
 * @Author: zouhongxue
 * @Date: 2019/4/22 11:38 PM
 */
public class Singleton {
    volatile private static Singleton singleton;

    public static Singleton getSingleton() {
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(getSingleton());
        }, "aaa").start();
        new Thread(() -> {
            System.out.println(getSingleton());
        }, "bbb").start();
        new Thread(() -> {
            System.out.println(getSingleton());
        }, "ccc").start();
    }
}
