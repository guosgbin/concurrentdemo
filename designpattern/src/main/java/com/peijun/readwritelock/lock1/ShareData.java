package com.peijun.readwritelock.lock1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 22:05
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ShareData {

    /**
     * 定义共享数据 资源
     */
    private final List<Character> container = new ArrayList<>();
    /**
     * 构造ReadWriteLock
     */
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    /**
     * 创建读取锁
     */
    private final Lock readLock = readWriteLock.readLock();

    /**
     * 创建写入锁
     */
    private final Lock writeLock = readWriteLock.writeLock();

    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add(i, 'c');
        }
    }

    public char[] read() throws InterruptedException {
        try {
            // 首先使用读锁进行lock
            readLock.lock();
            char[] newBuffer = new char[length];
            for (int i = 0; i < length; i++) {
                newBuffer[i] = container.get(i);
            }
            slowly();
            return newBuffer;
        } finally {
            // 操作结束之后释放锁
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            // 首先使用写锁进行lock
            writeLock.lock();
            for (int i = 0; i < length; i++) {
               this.container.add(i, c);
            }
            slowly();
        } finally {
            // 操作结束之后释放锁
            writeLock.unlock();
        }
    }


    /**
     * 模拟耗时操作
     */
    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
        }
    }
}
