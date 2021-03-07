package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试偏向锁
 *
 * 测试延迟
 * 测试延迟时间设置为0 线程退出同步代码块后 线程ID还是会保存的
 * <p>
 * -XX:BiasedLockingStartupDelay=0 -XX:+UseBiasedLocking
 *
 * -XX:-UseBiasedLocking
 */
public class BiasedLockDemo01 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000101
        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable()); // 101 且hash值和分代年龄都是0

        synchronized (MONITOR) {
            // 00000000 00000000 00000000 00000000 00000010 10011011 001100  00   0  0000    101
            // |<----                      线程ID                     ---->| 时间    分代年龄 锁状态
            System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable()); // 101
        }
        // 00000000 00000000 00000000 00000000 00000010 10011011 001100  00   0  0000    101
        // |<----                      线程ID                     ---->| 时间    分代年龄 锁状态
        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
    }
}
