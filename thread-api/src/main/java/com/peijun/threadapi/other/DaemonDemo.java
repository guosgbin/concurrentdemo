package com.peijun.threadapi.other;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/23 0:13
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试守护线程
 */
public class DaemonDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
            }
        }, "守护线程");

        t1.setDaemon(true);
        t1.start();
        System.out.println("主线程等待中...");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("我是主线程...我跑完了, 守护线程也停了");
    }
}
