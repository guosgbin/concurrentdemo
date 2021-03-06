package com.peijun.threadapi.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/17 23:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试stop方法 抛出ThreadDeath方法
 */
public class StopDemo03 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.currentThread().stop();
            } catch (ThreadDeath e) {
                System.out.println("我自己调了stop方法，抛出了ThreadDead异常");
            }
        });

        thread.start();
        TimeUnit.MICROSECONDS.sleep(1);
        System.out.println("结束...");
    }
}
