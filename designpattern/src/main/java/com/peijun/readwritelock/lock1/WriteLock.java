package com.peijun.readwritelock.lock1;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 21:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 设置为包可见
 *
 * 当有线程正在进行读操作或者写操作的时候，若当前线程试图获得锁，
 * 则其将会进入MONITOR的wait set中而阻塞，同时增加waitingWriter和writingWriter的数量
 * 但是当线程从wait set中被激活的时候waitingWriter将很快被减少
 *
 * 写释放锁，意味着writer的数量减少，事实上变成了0，同时唤醒wait中的线程，
 * 并将preferWriter修改为false，以提高读线程获得锁的机会
 */
class WriteLock implements Lock{

    private final ReadWriteLockImpl readWriteLock;

    WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }


    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMONITOR()) {
            try {
                // 首先使等待获取写入锁的数字加1
                readWriteLock.incrementWritingWriters();
                // 如果此时有其他线程正在进行读操作，或者写操作，那么当前线程将被挂起
                while (readWriteLock.getReadingReaders() > 0
                        || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMONITOR().wait();
                }
            } finally {
                // 成功获取到了写入锁，使得等待获取写入锁的计数器减一
                this.readWriteLock.decrementWatingWriters();
            }
            // 将正在写入的线程数量加1
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMONITOR()) {
            // 减少正在写入锁的线程计数器
            readWriteLock.decrementWritingWriters();
            // 将偏好状态修改为false，可以使得读锁被最快速的获得
            readWriteLock.changePrefer(false);
            // 通知唤醒其他在MONITOR的monitor waitset中的线程
            readWriteLock.getMONITOR().notifyAll();
        }
    }
}
