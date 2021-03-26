package com.peijun.waitnotify.guarded01;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 19:25
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GuardedObject guardedObject = new GuardedObject();
        // 线程A去计算题M
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "开始运行...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " ---> 计算题M完成...");
                String result = "我是题M的答案";
                guardedObject.putResult(result); // 存入答案
            } catch (InterruptedException e) {
            }
        }, "线程A").start();

        // 线程B计算题N，需要等待M题的答案
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始运行...");
            // 阻塞等待题N的答案
            Object result = guardedObject.getResult();
            System.out.println(Thread.currentThread().getName() +
                    " ---> 线程B获得了线程A的答案... " + result + " 解题N中...");
        }, "线程B").start();

        System.in.read();
    }
}
