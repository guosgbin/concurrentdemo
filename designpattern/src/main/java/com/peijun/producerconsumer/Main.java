package com.peijun.producerconsumer;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/22 22:10
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        TimberMill timberMill = new TimberMill(3);
        for (int i = 0; i < 3; i++) {
            new LumberjackThread("伐木工线程-" + i, timberMill).start();
        }

        TimeUnit.SECONDS.sleep(1);

        for (int i = 0; i < 3; i++) {
            new WokerThread("woker thread-" + i, timberMill).start();
        }
    }
}
