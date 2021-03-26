package com.peijun.waitnotify.guardedsuspension;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:44
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ServerThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Request request = requestQueue.getRequest();
                System.out.println(Thread.currentThread().getName() + " 处理 " + request);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException ignored) {
            }
        }
    }
}
