package com.peijun.threadapi.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/18 23:13
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试中断
 */
public class InterruptDemo01 {
    private final static int LOOP_COUNT = 1000000000;
    private final static int PRINT_COUNT = LOOP_COUNT / 10;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < LOOP_COUNT; i++) {
                if (i % PRINT_COUNT == 0) {
                    System.out.println("子线程还在运行..."+ (i / PRINT_COUNT));
                }
            }
        }, "子线程");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t1.interrupt();
        System.out.println("主线程调用了中断方法...");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("主线程结束...");
    }
}
