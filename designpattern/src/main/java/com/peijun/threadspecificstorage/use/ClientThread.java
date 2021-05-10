package com.peijun.threadspecificstorage.use;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/9 15:52
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ClientThread extends Thread{

    public ClientThread(String name) {
        super(name);
    }

    public void run() {
        System.out.println(getName() + " 开始...");
        Log.println(Thread.currentThread().getName()+ " 666");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
        }
        Log.close();
        System.out.println(getName() + " 结束...");
    }
}
