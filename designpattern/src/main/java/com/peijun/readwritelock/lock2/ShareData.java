package com.peijun.readwritelock.lock2;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 23:13
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 可以执行读取和写入的类
 * buffer是实际读写对象的char数组
 * lock是ReadWriteLock
 * 构造函数会根据传入的长度来分配一个char数组，并初始化buffer字段，同时以字符"#"填满buffer，"#"为初始值
 */
public class ShareData {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public ShareData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '#';
        }
    }

    public char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        System.arraycopy(buffer,0, newBuffer, 0, buffer.length);
        sleep(100);
        return newBuffer;
    }

    /**
     * 模拟耗时操作
     */
    private void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public void write(char c) throws InterruptedException {
        lock.writeLock();
        try {
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            sleep(100);
        }
    }
}
