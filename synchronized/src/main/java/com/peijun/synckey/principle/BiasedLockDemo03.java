package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试偏向锁
 * <p>
 * 测试 计算哈希值 撤销偏向锁
 * 当一个对象当前正处于偏向锁状态，并且需要计算其identity hash code的话，则它的偏向锁会被撤销。
 */
public class BiasedLockDemo03 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("=====锁对象的哈希值===== : " + MONITOR.hashCode());
        System.out.println("=====此时偏向锁状态 线程ID为0=====");
        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
        synchronized (MONITOR) {
            System.out.println("=====获取锁之后 -> 偏向锁状态 -> 存放线程ID=====");
            System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
            System.out.println("=====锁对象的哈希值===== : " + MONITOR.hashCode());
            System.out.println("=====哈希值之后 -> 重量级锁状态 -> 哈希值会存到ObjectMonitor=====");
            System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
        }
        System.out.println("=====释放锁之后 -> 重量级锁状态=====");
        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
    }
}
