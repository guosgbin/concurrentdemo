package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试偏向锁
 * <p>
 * 测试 计算哈希值 撤销偏向锁
 * 多个线程访问同步代码  偏向锁撤销为轻量级锁
 */
public class BiasedLockDemo04 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable task1 = () -> {
            for (int i = 0; i < 5; i++) {
                synchronized (MONITOR) {
                    System.out.println(Thread.currentThread().getName() + "第" + (i+1) + "次进入同步代码块");
                    System.out.println("=====偏向锁状态=====");
                    System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (MONITOR) {
                System.out.println("====="+Thread.currentThread().getName() + "争抢锁，已经拿到了=====");
                System.out.println("=====轻量级锁状态=====");
                System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
            }
        };
        Thread t1 = new Thread(task1, "线程1");
        Thread t2 = new Thread(task2, "争抢线程");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        System.out.println("=====退出同步代码 无锁状态=====");
        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
    }
}
