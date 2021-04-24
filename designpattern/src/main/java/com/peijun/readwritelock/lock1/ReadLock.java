package com.peijun.readwritelock.lock1;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 21:42
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 读锁
 *
 * 设置为 包可见
 *
 * 当没有任何线程对数据写操作的时候，读线程才有可能获得锁的拥有权，
 * 当然除此之外，为公平起见，如果当前有很多线程正在等待获得写锁的拥有权，
 * 同样读线程将会将入MONITOR的wait set中，readingReader的数量增加
 *
 * 读线程释放锁，这意味着reader的数量将减少一个，同时唤醒wait的线程，
 * reader唤醒的基本上都是由于获取写锁而进入阻塞的线程，为了提高写锁获得锁的机会，需要将preferWriter修改为true
 */
class ReadLock implements Lock{

    private final ReadWriteLockImpl readWriteLock;

    ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        // 使用MONITOR锁
        synchronized (readWriteLock.getMONITOR()) {
            // 若此时有线程在进行写操作
            // 或者有写线程正在等待并且偏向写锁的标识为true时，就会无法获得读锁，只能被挂起
            while (readWriteLock.getWritingWriters() > 0
                    || (readWriteLock.getPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMONITOR().wait();
            }
        }
        // 成功获得锁，并且使readingReaders的数量增加
        readWriteLock.incrementReadingReaders();
    }

    @Override
    public void unlock() {
        // 使用MONITOR作为锁，并且进行同步
        synchronized (readWriteLock.getMONITOR()) {
            // 释放锁的过程就是使当前reading的数量减一
            // 将preferWriter设置为true，可以使得writer线程获得更多的机会
            // 通知唤醒与MONITOR关联monitor waitset中的线程
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMONITOR().notifyAll();
        }
    }
}
