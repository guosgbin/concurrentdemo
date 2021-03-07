package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试偏向锁
 * <p>
 * 测试批量重偏向
 */
public class BiasedLockDemo05 {
    private static final Object MONITOR = new Object();
    // 在那些次数打印内存布局
    private static List<Integer> printTimes = Arrays.asList(4, 9, 14, 18, 19, 20, 24, 29);
    private static Vector vector = new Vector();

    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(() -> {
            LockSupport.park();
            for (int i = 0; i < 30; i++) {
                Object monitor = vector.get(i);
                if (printTimes.contains(i)) {
                    System.out.println("=====加锁前====="+ ClassLayout.parseInstance(MONITOR).toPrintable());
                }
                synchronized (monitor) {
                    if (printTimes.contains(i)) {
                        System.out.println(Thread.currentThread().getName() + "第" + i + "次进入同步代码块");
                        System.out.println("=====偏向锁状态=====");
                        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
                    }
                }
                if (printTimes.contains(i)) {
                    System.out.println("=====加锁后====="+ ClassLayout.parseInstance(MONITOR).toPrintable());
                }
            }
        }, "t2");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                Object monitor = new Object();
                vector.add(monitor);
                if (printTimes.contains(i)) {
                    System.out.println("=====加锁前====="+ ClassLayout.parseInstance(MONITOR).toPrintable());
                }
                synchronized (monitor) {
                    if (printTimes.contains(i)) {
                        System.out.println(Thread.currentThread().getName() + "第" + i + "次进入同步代码块");
                        System.out.println("=====偏向锁状态=====");
                        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
                    }
                }
                if (printTimes.contains(i)) {
                    System.out.println("=====加锁后====="+ ClassLayout.parseInstance(MONITOR).toPrintable());
                }
                System.out.println("=====t1线程都是偏向锁状态=====");
            }
            LockSupport.unpark(t2);
        }, "t1");

        t1.start();
//        t2.start();
    }
}
