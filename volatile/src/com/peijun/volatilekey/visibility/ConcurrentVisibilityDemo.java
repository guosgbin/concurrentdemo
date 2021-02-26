package com.peijun.volatilekey.visibility;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/21 19:04
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试可见性  有问题
 */
public class ConcurrentVisibilityDemo {
//    private static boolean init = false;
    private static volatile boolean init = false;

    public static void main(String[] args) throws InterruptedException {
        // 配置完成 启动任务线程
        Thread taskThread = new Thread(() -> {
            while (!init) {
//                System.out.println("好家伙，配置初始化好了，开始执行任务1...");
            }
            System.out.println("好家伙，配置初始化好了，开始执行任务...");
        });

        // 初始化配置线程
        Thread initThread = new Thread(() -> {
            init = true;
            System.out.println(Thread.currentThread().getName() + "修改了flag为false");
        });

        taskThread.start();
        TimeUnit.SECONDS.sleep(3);
        initThread.start();
    }
}
