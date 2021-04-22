package com.peijun.producerconsumer;

import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/22 22:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 木厂  用于缓存Lumber木材类
 * 此对象可以解决 生产者和消费者的速度不一致的现象
 */
public class TimberMill {
    /**
     * 使用阻塞队列来缓存 木材
     */
    private final LinkedList<Lumber> queue;

    /**
     * 最多缓存木材的个数
     */
    private final int capacity;

    public TimberMill(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    /**
     * 添加木材 同一个TimberMill对象的put和take的操作是互斥的
     */
    public synchronized void put(Lumber lumber) throws InterruptedException {
        // 守护条件
        while (queue.size() >= capacity) {
            // 缓存的木材个数到最大限制了 就去等待木材消费
            System.out.println(Thread.currentThread().getName() + "木厂放不下了...");
            this.wait();;
        }
        // 队列未满，需要添加木材到队列
        queue.offer(lumber);
        // 唤醒 获取木材的线程
        this.notifyAll();
    }

    /**
     * 获取木材 同一个TimberMill对象的put和take的操作是互斥的
     */
    public synchronized Lumber take() throws InterruptedException {
        // 守护条件
        while (queue.isEmpty()) {
            // 木厂没有木材了，等待生产
            System.out.println(Thread.currentThread().getName() + " --> 木厂没有木材了...");
            this.wait();
        }
        // 队列中还有木材，拿出，并唤醒生产阻塞的线程
        // 下面两行代码的顺序其实不重要，因为都是在一个同步方法中，
        // 就算唤醒其他线程，最后的poll代码执行完才会释放锁
        this.notifyAll();
        return queue.poll();
    }
}
