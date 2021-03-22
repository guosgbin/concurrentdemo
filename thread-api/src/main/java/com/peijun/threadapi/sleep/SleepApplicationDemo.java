package com.peijun.threadapi.sleep;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/21 17:10
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试sleep防止CPU占用超过100%
 */
public class SleepApplicationDemo {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            // do nothing
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }
}
