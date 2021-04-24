package com.peijun.readwritelock.lock2;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 22:48
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ReadWriteLock {
    /**
     * 当前正在读取中的线程个数
     */
    private int readingReaders = 0;

    /**
     * 当前正在等待写操作的线程个数
     */
    private int waitingWriters = 0;

    /**
     * 当前正在写入中的线程个数 最多只能为1
     */
    private int writingWriters = 0;

    /**
     * true表示写入优先
     * false表示读取优先
     * 这是为了不降低线程的生存性而设计的
     */
    private boolean preferWriter = true;

    public synchronized void readLock() throws InterruptedException {
        // case1：当先正在写操作的线程大于0 表示已经有线程拿到写锁了，不能拿写锁了
        // case2：有写线程正在等待并且偏向写锁的标识为true时，就会无法获得读锁，只能被挂起
        while (writingWriters > 0
                || (preferWriter && waitingWriters > 0)) {
            this.wait();
        }
        // 当前正在读取的线程个数加1
        readingReaders++;
    }

    public synchronized void readUnlock() {
        // 正在读取的线程减1
        readingReaders--;
        // 将preferWriter设置为true，可以使得writer线程获得更多的机会
        preferWriter = true;
        // 唤醒wait set中等待的线程
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        // 等待获得写锁的线程加1
        waitingWriters++;
        // case1:
        // case2:
        try {
            while (writingWriters > 0 || readingReaders > 0) {
                this.wait();
            }
        } finally {
            // 当前正在等待写入的线程减1，保证等待写入的计数器减1
            waitingWriters--;
        }
        // 当前获得写锁的线程加1，最多也只能是1
        writingWriters++;
    }

    public synchronized void writeUnlock() {
        // 当前正在写入的线程减1
        writingWriters--;
        // 将偏好状态修改为false，可以使得读锁被最快速的获得
        preferWriter = false;
        // 唤醒的wait set中的线程。
        this.notifyAll();
    }
}
