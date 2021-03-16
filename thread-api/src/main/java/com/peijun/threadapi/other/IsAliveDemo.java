package com.peijun.threadapi.other;

import java.util.concurrent.TimeUnit;

/**
 * 测试线程是否存活
 */
public class IsAliveDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("我在等待中..");
            sleep(1);
        });
        t1.start();
        System.out.println("子线程是否存活 --> " + t1.isAlive());
        sleep(2);
        System.out.println("子线程是否存活 --> " + t1.isAlive());
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ignored) {
        }
    }
}
