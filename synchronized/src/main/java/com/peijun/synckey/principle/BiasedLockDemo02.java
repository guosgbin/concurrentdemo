package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试偏向锁  两个线程 有竞争
 */
public class BiasedLockDemo02 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 0; i < 5; i++) {
                synchronized (MONITOR) {
                    System.out.println(Thread.currentThread().getName() + "第" + (i+1) + "次进入同步代码块");
                    System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
                }
            }
        };

        Runnable task2 = () -> {
            synchronized (MONITOR) {
                System.out.println(Thread.currentThread().getName() + "争抢锁，已经拿到了");
                System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
            }
        };
        Thread t1 = new Thread(task1, "线程1");
        Thread t2 = new Thread(task2, "争抢线程");
        t1.start();
        t2.start();
    }
}
