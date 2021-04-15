package com.peijun.guardedsuspension.basic;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:44
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ServerThread extends Thread {
    private final MessageQueue messageQueue;

    public ServerThread(MessageQueue messageQueue, String name) {
        super(name);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Message message = messageQueue.getMessage();
                System.out.println(Thread.currentThread().getName() + " deal " + message.getName());
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException ignored) {
            }
        }
    }
}
