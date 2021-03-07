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
 * <p>
 * 测试 计算哈希值 撤销偏向锁
 * 当一个对象已经计算过 identity hash code，它就无法进入偏向锁状态
 */
public class BiasedLockDemo02 {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=====锁对象的哈希值===== : " + MONITOR.hashCode());
        System.out.println("=====计算哈希值之后 此时偏向锁被撤销了 -> 无锁状态 -> 存放哈希值=====");
        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
        synchronized (MONITOR) {
            System.out.println("=====获取锁之后 -> 轻量锁状态 -> 哈希值会存到Lock Record 在MarkWord存执行栈中的Lock Record的地址=====");
            System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
        }
        System.out.println("=====释放锁之后 -> 无锁状态 -> 哈希值替换回来=====");
        System.out.println(ClassLayout.parseInstance(MONITOR).toPrintable());
    }
}
