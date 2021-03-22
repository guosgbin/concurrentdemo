package com.peijun.threadapi.safeexit;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/22 10:52
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 安全退出  通过判断中断标志退出
 */
public class SafeExit01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("线程1开始运行...");
            while (!Thread.currentThread().isInterrupted()) {
                // 例如 心跳操作
            }
            System.out.println("线程1结束运行...");
        }, "线程1");

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
        System.out.println("我要打断线程1...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程退出...");
    }
}
