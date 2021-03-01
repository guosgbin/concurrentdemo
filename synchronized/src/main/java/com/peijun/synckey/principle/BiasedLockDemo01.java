package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试偏向锁  单个线程 无竞争
 */
public class BiasedLockDemo01 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                synchronized (MONITOR) {
                    System.out.println(Thread.currentThread().getName() + "第" + (i+1) + "次进入同步代码块");
                    System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
                }
            }
        };
        Thread t = new Thread(task, "线程1");
        t.start();
    }
}
