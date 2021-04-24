package com.peijun.readwritelock.lock2;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 23:32
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试类
 */
public class Main {
    public static void main(String[] args) {
        ShareData shareData = new ShareData(10);
        // 读取线程
        for (int i = 0; i < 6; i++) {
            new ReaderThread(shareData).start();
        }
        // 写入线程
        new WriterThread(shareData, "ABCDEFGHIJK").start();
        new WriterThread(shareData, "abcdrfghijk").start();
    }
}
