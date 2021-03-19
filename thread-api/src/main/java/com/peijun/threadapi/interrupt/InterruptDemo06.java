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
public class InterruptDemo06 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程的中断状态1 ---> " + Thread.currentThread().isInterrupted()); // false
        // 中断主线程
        Thread.currentThread().interrupt();
        System.out.println("主线程的中断状态2 ---> " + Thread.interrupted()); // true 只有第一次是true
        System.out.println("主线程的中断状态3 ---> " + Thread.interrupted()); // false
        System.out.println("主线程的中断状态4 ---> " + Thread.interrupted()); // false
        // 再次中断主线程
        Thread.currentThread().interrupt();
        System.out.println("主线程的中断状态5 ---> " + Thread.interrupted()); // true 重新中断线程后 第一次还是true
        System.out.println("主线程的中断状态6 ---> " + Thread.interrupted()); // false
    }
}

