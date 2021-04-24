package com.peijun.readwritelock.lock1;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 21:14
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public interface Lock {

    /**
     * 获取锁，在此期间可能会阻塞
     */
    void lock() throws InterruptedException;

    /**
     * 释放锁
     */
    void unlock();
}
