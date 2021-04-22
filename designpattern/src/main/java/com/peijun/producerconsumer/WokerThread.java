package com.peijun.producerconsumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/22 22:10
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 消费者线程
 */
public class WokerThread extends Thread{
    /**
     * 木厂对象 生产和消费的线程用的是一个对象
     */
    private TimberMill timberMill;
    private Random random = new Random();

    public WokerThread(String name, TimberMill timberMill) {
        super(name);
        this.timberMill = timberMill;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 从木厂中获取木材
                Lumber lumber = timberMill.take();
                System.out.println(getName() + " = 拿到了< " + lumber.getName() + "> 去盖房子了");
                TimeUnit.SECONDS.sleep(random.nextInt(3)); // 模拟耗时
            }
        } catch (InterruptedException ignored) {
        }
    }
}
