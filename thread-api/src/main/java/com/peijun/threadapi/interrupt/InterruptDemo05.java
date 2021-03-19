package com.peijun.threadapi.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/18 23:13
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试中断 isInterrupted方法
 */
public class InterruptDemo05 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("在睡觉中被别人打断了...");
                System.out.println("调用中断方法后的子线程中断状态1 ---> " + Thread.currentThread().isInterrupted()); // fasle
            }
        }, "子线程");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("调用中断方法前的子线程中断状态 ---> " + t1.isInterrupted()); // fasle
        t1.interrupt();
        // fasle 抛出InterruptedException时，中断标志会被清除
        t1.join();
        System.out.println("调用中断方法后的子线程中断状态2 ---> " + t1.isInterrupted()); // fasle
        TimeUnit.SECONDS.sleep(2);
        System.out.println("调用中断方法后的子线程中断状态3 ---> " + t1.isInterrupted()); // fasle
        System.out.println("主线程结束...");
    }
}

