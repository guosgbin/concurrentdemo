package com.peijun.threadapi.stopthread;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/17 23:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试stop方法 不释放锁
 */
public class StopDemo02 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (MONITOR) {
                System.out.println("子线程开始...");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ignored) {
                }
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.stop(); // 停止线程t1
        // 在主线程尝试获取锁
        System.out.println("在主线程尝试获取锁...");
        synchronized (MONITOR) {
            System.out.println("主线程拿到锁...");
        }
        System.out.println("子线程的状态：" + t1.getState());
        System.out.println("结束...");
    }
}
