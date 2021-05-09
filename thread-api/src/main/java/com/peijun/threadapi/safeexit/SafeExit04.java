package com.peijun.threadapi.safeexit;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/22 10:52
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 安全退出 volatile标志退出
 */
public class SafeExit04 {

    static class Task implements Runnable {
        private volatile boolean exitFlag = false; // 线程退出标志
//        private boolean exitFlag = false; // 线程退出标志

        @Override
        public void run() {
            System.out.println("线程1开始运行...");
            while (!exitFlag) {
                // 业务操作
            }
            System.out.println("线程1结束运行...");
        }

        public void shutdown(Thread thread) {
            exitFlag = true;
            thread.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread = new Thread(task, "线程1");
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("关闭前");
        task.shutdown(thread);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程退出...");
    }
}
