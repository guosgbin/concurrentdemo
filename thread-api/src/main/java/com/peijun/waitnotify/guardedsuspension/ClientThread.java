package com.peijun.waitnotify.guardedsuspension;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ClientThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue, String name, long seed) {
        super(name);
        this.requestQueue = requestQueue;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Request request = new Request("No." + i);
                System.out.println(Thread.currentThread().getName() + " 请求 " + request);
                requestQueue.putRequest(request);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            } catch (InterruptedException ignored) {
            }
        }
    }
}
