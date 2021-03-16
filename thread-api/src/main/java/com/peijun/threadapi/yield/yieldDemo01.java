package com.peijun.threadapi.yield;

/**
 * 测试线程让步
 */
public class yieldDemo01 {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = (() -> {
            int count = 0;
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 100000000; i++) {
                // 让步时间  不是让步时间 请求耗时: 8864  请求耗时: 1
                Thread.yield(); // 线程让步 让出CPU执行权
            }
            long endTime = System.currentTimeMillis();
            System.out.println("请求耗时: " + (endTime - startTime));
        });

        Thread t1 = new Thread(task);
        t1.start();
        t1.join();
    }
}
