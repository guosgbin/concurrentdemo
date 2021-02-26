package com.peijun.volatilekey.visibility;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/26 8:49
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class VisibilityDemo {
    final static int MAX = 5;
    static volatile int init_value = 0;

    public static void main(String[] args) {
        // 启动一个Reader线程，当发现local_value和init_value不同时，则输出init_value被修改的信息
        Thread reader = new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (init_value != localValue) {
                    System.out.println("The init is updated to" + init_value);
                }
                // 对Localvalue重新赋值
                localValue = init_value;
            }
        }, "Reader");

        // 启动一个Updater线程，主要用于对init_value的修改，当local_value>=5的时候则退出生命周期
        Thread updater = new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                // 修改init_value
                System.out.println("The init_value will be change to" + ++localValue);
                init_value = localValue;

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {

                }
            }
        }, "Updater");

        updater.start();
        reader.start();

    }
}
