package com.peijun.guardedsuspension.multiple;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:46
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        // 外卖骑手线程 送一份外卖
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "开始运行...");
                TimeUnit.SECONDS.sleep(1); // 模拟耗时
                Message message = new Message("我是一个外卖");
                System.out.println("骑手马上到你家门口 --> " + message.getName());
                messageQueue.putMessage(message);
            } catch (InterruptedException ignored) {
            }
        }, "外卖骑手").start();
        // 点外卖的用户，等待外卖送达
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "开始运行...");
            // 此时调用带超时的方法
            Message message = messageQueue.getMessage(2000);
            if (message == null) {
                System.out.println("骑手也太磨叽了，我等两年了还没送到，我不等了，自己出去吃了");
            } else {
                System.out.println("哇，外卖到了，开吃开吃 --> " + message.getName());
            }
        }, "Bob").start();
    }
}
