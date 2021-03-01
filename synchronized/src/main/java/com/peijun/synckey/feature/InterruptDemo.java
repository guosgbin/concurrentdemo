package com.peijun.synckey.feature;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 15:58
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class InterruptDemo {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            synchronized (InterruptDemo.class) {
                try {
                    int count = 0;
                    while (++count < 60) {
                        System.out.println(Thread.currentThread().getName() + "第" + count + "次抢到锁了");
                        TimeUnit.SECONDS.sleep(1);
                        if (Thread.currentThread().isInterrupted()) {
                            return;
                        }
                    }
                } catch (InterruptedException e) {
                    System.out.println("666");
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(5);
//        t1.interrupt();
//        System.out.println(t1.isInterrupted());
        t2.interrupt();
        System.out.println(t1.getState());
        System.out.println(t2.getState());

    }
}
