package com.peijun.synckey.application;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 15:30
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 同步方法
 */
public class SyncMethod {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            t.start();
            threadList.add(t);
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        System.out.println("累加结果：" + count);

    }

//    public static void increase() {
//        synchronized (SyncMethod.class) {
//            count++;
//        }
//    }

    public static synchronized void increase() {
        count++;
    }
}
