package com.peijun.synckey.feature;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 15:58
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class InterruptDemo03 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + "开始执行任务");
            synchronized (MONITOR) {
                try {
                    System.out.println(name + "拿到MONITOR锁了");
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(name + "等待了20秒，执行完毕");
                } catch (InterruptedException e) {
                    System.out.println("t1 interruptedException");
                }
            }
        };

        Runnable task2 = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + "开始执行任务");
            synchronized (MONITOR) {
                try {
                    System.out.println(name + "拿到MONITOR锁了");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(name + "等待了1秒，执行完毕");
                } catch (InterruptedException e) {
                    System.out.println(name + "被中断了");
                }
            }
        };

        Thread t1 = new Thread(task1, "线程1");
        Thread t2 = new Thread(task2, "线程2");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        // 中断t2线程的执行
        t2.interrupt();
        System.out.println("t2线程中断标志修改..");
    }
}
