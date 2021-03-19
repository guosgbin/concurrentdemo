package com.peijun.threadapi.interrupt;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/18 23:13
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 汪文君 案例
 */
public class InterruptDemo03 {
    public static void main(String[] args) throws InterruptedException {
//        test01();
//        test02();
//        test03();
//        test04();
        test05();
    }

    /**
     * 测试中断可中断的方法 例如sleep这种
     */
    private static void test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("M的，我被中断了");
            }
        });
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        t1.interrupt();
    }

    /**
     * 测试 中断前后的中断状态
     */
    private static void test02() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                // do nothing
            }
        });
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("t1线程的状态 ---> " + t1.isInterrupted());
        t1.interrupt();
        System.out.println("t1线程的状态 ---> " + t1.isInterrupted());
    }

    /**
     * 测试抛出InterruptedException 清除中断标志
     */
    private static void test03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    // 此处中断标志会被清除
                    System.out.println("M的，我被中断了");
                }
            }
        });
        t1.setDaemon(true);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("t1线程的状态 ---> " + t1.isInterrupted());
        t1.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("t1线程的状态 ---> " + t1.isInterrupted());
    }

    /**
     * 测试静态interrupted方法，
     * 该方法会清除中断标志，也就是说在调用中断方法后，第一次返回true，后清除中断标志，第二次之后都是返回fasle
     */
    private static void test04() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("中断状态 ---> " + Thread.interrupted());
            }
        });
        t1.setDaemon(true);
        t1.start();
        TimeUnit.MILLISECONDS.sleep(1);
        t1.interrupt();
    }

    /**
     * 测试在 可中断方法之前就调用中断方法
     * 结果是仍然会被捕获
     */
    private static void test05() {
        System.out.println("主线程是否被中断 ---> " + Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("主线程是否被中断 ---> " + Thread.currentThread().isInterrupted());
//        System.out.println("主线程是否被中断 ---> " + Thread.interrupted());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("我被中断了");
        }
    }
}

