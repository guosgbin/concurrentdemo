package com.peijun.threadapi.join;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/17 21:31
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试Join
 */
public class JoinDemo01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("子线程开始执行...");
                TimeUnit.SECONDS.sleep(100000); // 睡眠2秒
            } catch (InterruptedException ignored) {
            }
        }, "子线程");
        thread.start(); // 开启线程
        long startTime = System.currentTimeMillis();
        thread.join();
        long endTime = System.currentTimeMillis();
        System.out.printf("主线程等待了%s毫秒%n", (endTime - startTime));
        System.out.println("主线程在运行...");
    }
}
