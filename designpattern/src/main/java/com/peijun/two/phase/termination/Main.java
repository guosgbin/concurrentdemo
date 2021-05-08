package com.peijun.two.phase.termination;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/8 8:48
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始...");
        // 创建线程并启动
        MonitorThread monitor = new MonitorThread();
        monitor.start();

        // 让线程运行5秒钟
        TimeUnit.SECONDS.sleep(5);

        // 发出终止线程的请求
        monitor.shutdown();

        monitor.join();
        System.out.println("主线程结束...");
    }
}
