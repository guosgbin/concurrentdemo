package com.peijun.producerconsumer;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/22 22:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 伐木工  生产线程
 */
public class LumberjackThread extends Thread{
    /**
     * 木厂对象 生产和消费的线程用的是一个对象
     */
    private TimberMill timberMill;
    private Random random = new Random();

    public LumberjackThread(String name, TimberMill timberMill) {
        super(name);
        this.timberMill = timberMill;
    }

    private static AtomicInteger id = new AtomicInteger(0);

    /**
     * 获取木材编号
     */
    public int nextId() {
        return id.incrementAndGet();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 创建一个木材对象，放入队列中
                TimeUnit.SECONDS.sleep(random.nextInt(3)); // 模拟砍树的耗时
                Lumber lumber = new Lumber("木材-" + nextId());
                System.out.println(getName() + " = 加工好了 <" + lumber.getName() + ">");
                timberMill.put(lumber);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
