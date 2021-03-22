package com.peijun.threadapi.suspend;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/22 18:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试暂停线程 独占锁资源 System.out.println 里面有同步操作
 */
public class SuspendDemo03 {
    private static LongAdder adder = new LongAdder();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                adder.increment(); // 自增操作
                System.out.println("我是打印语句，我源码里面有同步操作 --> " + adder.sum());
            }
        }, "子线程");

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.suspend();
        System.out.println(adder.sum());
    }
}
