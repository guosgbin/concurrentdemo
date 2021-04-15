package com.peijun.guardedsuspension.basic;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ClientThread extends Thread {
    private final MessageQueue messageQueue;

    public ClientThread(MessageQueue messageQueue, String name) {
        super(name);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Message message = new Message("消息-" + i);
                System.out.println(Thread.currentThread().getName() + " 请求 " + message.getName());
                messageQueue.putMessage(message);
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException ignored) {
            }
        }
    }
}
