package com.peijun.workerthread.demo01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/26 23:36
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 发送请求的类
 */
public class ClientThread extends Thread{
    private final Channel channel;
    private final Random random = new Random();

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (true) {
                // 创建一个请求放到请求队列中
                Request request = new Request(getName(), i++);
                channel.putRequest(request);
                TimeUnit.SECONDS.sleep(random.nextInt(5));
            }
        } catch (InterruptedException ignored) {
        }
    }
}
