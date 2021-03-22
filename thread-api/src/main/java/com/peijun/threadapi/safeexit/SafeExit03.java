package com.peijun.threadapi.safeexit;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/22 10:52
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 安全退出 两阶段终止
 */
public class SafeExit03 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("线程1开始运行...");
            while (true) {
                Thread currentThread = Thread.currentThread();
                try {
                    if (currentThread.isInterrupted()) {
                        // 判断是否被中断
                        System.out.println("啊...线程1被中断了，要退出了");
                        break;
                    }
                    TimeUnit.MILLISECONDS.sleep(1); // 可能在这里被打断
                    System.out.println("我在执行业务操作..."); // 也可能在这里被打断
                } catch (InterruptedException e) {
                    // 假如在可中断方法时被中断，因为清除标志会清空，所以需要重置清除标志
                    System.out.println(e.getMessage());
                    System.out.println("爷重新设置中断标志...");
                    currentThread.interrupt(); // 重新设置清除标志 会在下一次循环中判断而退出
                }
            }
            System.out.println("线程1结束运行...");
        }, "线程1");

        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
        System.out.println("我要打断线程1...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程退出...");
    }
}
