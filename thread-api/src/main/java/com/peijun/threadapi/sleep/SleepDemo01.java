package com.peijun.threadapi.sleep;

import java.util.concurrent.TimeUnit;

/**
 * 测试睡眠
 */
public class SleepDemo01 {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        TimeUnit.MILLISECONDS.sleep(1);
    }
}
