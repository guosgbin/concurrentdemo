package com.peijun.thread.specific.storage.use;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/9 15:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        new ClientThread("Alcie").start();
        new ClientThread("Bobby").start();
        new ClientThread("Chris").start();
    }
}
