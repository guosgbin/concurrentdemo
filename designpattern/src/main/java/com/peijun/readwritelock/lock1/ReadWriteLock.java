package com.peijun.readwritelock.lock1;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 21:16
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 虽然名字是ReadWriteLock，但是这个类并不是锁，主要是用于创建 读锁 和 写锁 的
 * 并且提供了查询功能用于查询当前有多少个 reader和writer 和 waiting中的writer
 *
 * 假如reader的个数为0，那就意味着writer的个数等于0
 * 反之writer的个数大于0（writer最多只能为1），则reader的个数等于0
 */
public interface ReadWriteLock {

    /**
     * 创建读锁
     */
    Lock readLock();

    /**
     * 创建写锁
     */
    Lock writeLock();

    /**
     * 获取当前有多少线程正在执行写操作，最多是1个线程正在执行写操作
     */
    int getWritingWriters();

    /**
     * 获取当前有多少线程正在等待获取写锁，这些线程阻塞
     */
    int getWaitingWriters();

    /**
     * 获取当前有多少线程正在等待获取读锁
     */
    int getReadingReaders();

    /**
     * 工厂方法，创建ReadWriteLock
     */
    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    /**
     * 工厂方法，创建ReadWriteLock，并传入preferWriter
     * @return
     */
    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
