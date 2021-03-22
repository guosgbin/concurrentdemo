package com.peijun.threadapi.suspend;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/22 18:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试暂停和恢复线程
 */
public class SuspendDemo01 {
    private static LongAdder adder = new LongAdder();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
           while (true) {
               adder.increment(); // 自增操作
           }
        }, "子线程");

        t1.start(); // 开启线程
        TimeUnit.SECONDS.sleep(2);

        t1.suspend(); // 暂停
        currentTime("位置A"); // 打印
        TimeUnit.SECONDS.sleep(2);
        currentTime("位置A"); // 打印

        t1.resume(); // 恢复
        TimeUnit.SECONDS.sleep(2);

        t1.suspend(); // 继续暂停
        currentTime("位置B"); // 打印
        TimeUnit.SECONDS.sleep(2);
        currentTime("位置B"); // 打印
    }

    public static void currentTime(String name) {
        long timestamp = System.currentTimeMillis();
        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        System.out.printf("--< %s >-- : 时间戳 --> %s, 自增位置 --> %s%n",
                name, time, adder.sum());
    }
}
