package com.peijun.threadapi.priority;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程优先级
 */
public class PriorityDemo01 {
    private static volatile boolean isBreak = true;

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count1 = new AtomicInteger(0);
        AtomicInteger count2 = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            while (isBreak) {
                count1.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            while (isBreak) {
                count2.incrementAndGet();
            }
        });
        t1.setPriority(10);
        t2.setPriority(1);
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        isBreak = false;
        t1.join();
        t2.join();
        System.out.println(count1);
        System.out.println(count2);
        System.out.println("结束...");
    }
}
