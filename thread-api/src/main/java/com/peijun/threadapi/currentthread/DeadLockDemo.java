package com.peijun.threadapi.currentthread;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 获取死锁的线程
 */
public class DeadLockDemo {
    private static final Object MONITOR_1 = new Object();
    private static final Object MONITOR_2 = new Object();

    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(() -> {
            synchronized (MONITOR_1) {
                System.out.println("线程1 拿到了MONITOR_1, 等待获取MONITOR_2");
                sleepNo(500); // 睡眠500ms
                synchronized (MONITOR_2) {

                }
            }
        }, "线程1");

        Thread t2 = new Thread(() -> {
            synchronized (MONITOR_2) {
                System.out.println("线程2 拿到了MONITOR_2, 等待获取MONITOR_1");
                sleepNo(500); // 睡眠500ms
                synchronized (MONITOR_1) {

                }
            }
        }, "线程2");

        t1.start();
        t2.start();
        sleepNo(500); // 睡眠500ms
        System.out.println(t1.getId() +" ,"+ t2.getId());
        System.out.println("好像锁住了...");

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        System.out.println("死锁的线程" + Arrays.toString(deadlockedThreads));
        System.in.read();
    }

    public static void sleepNo(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
