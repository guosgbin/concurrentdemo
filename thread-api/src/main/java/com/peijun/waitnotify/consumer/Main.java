package com.peijun.waitnotify.consumer;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/5 22:52
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MessageQueue queue = new MessageQueue(2);

        // 生产线程
        for (int i = 0; i < 3; i++) {
            int messageId = i;
            new Thread(() -> {
                queue.put(new Message(messageId, "消息数据" + messageId));
            }, "生产线程" + i).start();
        }

        // 消费线程
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                Message take = queue.take();
            }
        }).start();
    }
}
