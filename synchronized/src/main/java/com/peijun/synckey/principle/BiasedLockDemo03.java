package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试偏向锁  两个线程 无竞争  也就是 挨个挨个拿锁
 *
 * https://blog.csdn.net/fycghy0803/article/details/74910238 Java并发之彻底搞懂偏向锁升级为轻量级锁
 */
public class BiasedLockDemo03 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
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
        Thread t2 = new Thread(task2, "争抢线程t2");
        Thread t3 = new Thread(task2, "争抢线程t3");
        t1.start();
        // 睡眠3秒 确保t1线程执行完毕 保证两个线程不争抢锁
        TimeUnit.SECONDS.sleep(3);
//        t1.join(); // join 会变为重量级锁
        t2.start();
//        TimeUnit.SECONDS.sleep(3);
        t2.join();
        t3.start(); // 此处可能打印 00 是轻量级锁  多打印几次就是101了-偏向锁
    }
}
