package com.peijun.threadapi.suspend;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/22 18:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试暂停线程 独占锁资源
 */
public class SuspendDemo02 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (MONITOR) {
                System.out.println("子线程进入了...我会永久暂停线程...");
                Thread.currentThread().suspend(); // 暂停线程
            }
        }, "永久暂停线程");

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程尝试获取锁...");
        synchronized (MONITOR) {
            System.out.println("主线程获取锁成功...");
        }
    }
}
