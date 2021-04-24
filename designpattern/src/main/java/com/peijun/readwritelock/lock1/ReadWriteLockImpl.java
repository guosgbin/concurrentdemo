package com.peijun.readwritelock.lock1;

import java.util.Objects;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 21:24
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 不是public修饰 包可见
 * 创建时使用{@link ReadWriteLock}的创建方法
 */
class ReadWriteLockImpl implements ReadWriteLock{

    /**
     * 定义对象锁
     */
    private final Object MONITOR = new Object();

    /**
     * 当前有多少线程正在 写入
     */
    private int writingWriters = 0;

    /**
     * 当前有多少线程正在 等待 写入
     */
    private int waitingWriters = 0;

    /**
     * 当前有多少线程正在 读
     */
    private int readingReaders = 0;

    /**
     * read和write的偏好设置
     */
    private boolean preferWriter;

    /**
     * 默认情况下preferWriter为true
     */
    public ReadWriteLockImpl() {
        this(true);
    }

    /**
     * 传入preferWriter
     */
    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    /**
     * 创建读锁
     */
    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    /**
     * 创建写锁
     */
    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    /**
     * 使写线程的数量增加
     */
    void incrementWritingWriters() {
        this.writingWriters++;
    }

    /**
     * 使等待写入的线程数量增加
     */
    void incrementWaitingWriters() {
        this.waitingWriters++;
    }

    /**
     * 使读线程的数量增加
     */
    void incrementReadingReaders() {
        this.readingReaders++;
    }

    /**
     * 使写线程的数量减少
     */
    void decrementWritingWriters() {
        this.writingWriters--;
    }

    /**
     * 使读取线程的数量减少
     */
    void decrementReadingReaders() {
        this.readingReaders--;
    }

    /**
     * 使等待获取写入锁的数量减一
     */
    void decrementWatingWriters() {
        this.waitingWriters--;
    }

    /**
     * 获取当前有多少个线程正在进行写操作
     */
    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    /**
     * 获取当前有多少线程正在等待获取写入锁
     */
    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    /**
     * 获取当前多少个线程正在进行读操作
     */
    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    /**
     * 获取对象锁
     */
    Object getMONITOR() {
        return this.MONITOR;
    }

    /**
     * 获取当前是否是偏向写锁
     */
    boolean getPreferWriter() {
        return this.preferWriter;
    }

    /**
     * 设置写锁偏好
     */
    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
