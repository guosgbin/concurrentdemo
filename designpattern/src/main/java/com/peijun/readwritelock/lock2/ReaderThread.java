package com.peijun.readwritelock.lock2;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 23:27
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 表示的是执行读取操作的线程
 * 该类会循环调用ShareData的read方法，并显示读取到char数组
 */
public class ReaderThread extends Thread{
    private final ShareData shareData;

    public ReaderThread(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] readBuffer = shareData.read();
                System.out.println(Thread.currentThread().getName() + " read " + new String(readBuffer));
            }
        } catch (InterruptedException ignored) {
        }
    }
}
