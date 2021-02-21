package com.peijun.jmm.atomicity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/21 19:04
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试原子性
 */
public class ConcurrentAtomicityDemo {
    // 1.共享变量
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            // 2.开启多个线程自增
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
            }, "线程" + i);
            thread.start();
            threadList.add(thread);
        }

        for (Thread thread : threadList) {
            thread.join(); // 等待前面的线程执行完毕
        }
        // 最后打印出来的count值一般都是小于5000的
        System.out.println(count);
    }
}