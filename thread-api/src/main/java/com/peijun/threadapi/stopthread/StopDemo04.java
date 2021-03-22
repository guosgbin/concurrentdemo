package com.peijun.threadapi.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/17 23:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试stop方法 释放锁造成数据不一致的现象
 */
public class StopDemo04 {
    private static int a = 100;
    private static int b = 100;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                assign();
            } catch (InterruptedException ignored) {
            }
        });
        thread.start();
        TimeUnit.MICROSECONDS.sleep(1);
        thread.stop(); // 暴力停止
        System.out.println("a = " + a + " b = " + b);
        System.out.println("结束...");
    }

    /** 赋值操作 */
    private synchronized static void assign() throws InterruptedException {
        a = 28;
        TimeUnit.SECONDS.sleep(5);
        b = 38;
    }
}
