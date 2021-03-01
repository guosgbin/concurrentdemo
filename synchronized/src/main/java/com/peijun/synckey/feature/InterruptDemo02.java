package com.peijun.synckey.feature;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 16:41
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class InterruptDemo02 {
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();
        Thread.sleep(2000);
        System.out.println("before interrupt");
        t1.interrupt();
        t2.interrupt();
        System.out.println("end interrupt");

        System.out.println(t1.getState());
        System.out.println(t2.getState());

        t1.join();
        t2.join();
    }

    static Thread t1 = new Thread(() -> {
        synchronized (o1){
            try {
                System.out.println("start t1");
                TimeUnit.SECONDS.sleep(1);
                synchronized (o2){
                    System.out.println("t1 lock o2");
                }
            } catch (InterruptedException e) {
                System.out.println("t1 interrupted");
                e.printStackTrace();
            }
        }
    });

    static Thread t2 = new Thread(() -> {
        synchronized (o2){
            try {
                System.out.println("start t2");
                TimeUnit.SECONDS.sleep(1);
                synchronized (o1){
                    System.out.println("t2 lock o1");
                }
            } catch (InterruptedException e) {
                System.out.println("t2 intterrupted");
                e.printStackTrace();
            }
        }
    });
}
