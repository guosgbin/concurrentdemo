package com.peijun.volatilekey.application;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/26 14:55
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * volatile在单例模式应用
 */
public class Singleton {
    private static volatile Singleton INSTANCE;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(instance);
        });

        Thread t2 = new Thread(() -> {
            Singleton instance = Singleton.getInstance();
            System.out.println(instance);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
