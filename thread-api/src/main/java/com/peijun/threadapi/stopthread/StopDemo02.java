package com.peijun.threadapi.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/17 23:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试stop方法
 */
public class StopDemo02 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("子线程开始...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ignored) {
            }
        });

        long startTime = System.currentTimeMillis();
        t1.start();
        t1.stop();
        System.out.println("子线程的状态：" + t1.getState());
    }
}
