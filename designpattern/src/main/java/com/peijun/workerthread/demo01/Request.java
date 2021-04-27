package com.peijun.workerthread.demo01;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/26 22:48
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 代表请求的类
 */
public class Request {
    private final String name;
    private final int number;
    private final Random random = new Random();

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute() {
        try {
            System.out.println(Thread.currentThread().getName() + " 执行 " + this);
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
