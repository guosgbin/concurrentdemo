package com.peijun.guardedsuspension.multiple;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/19 22:07
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 存放消息/请求的消息队列
 */
public class MessageQueue {
    /**
     * 队列ID
     */
    private Integer queueId;

    /**
     * 存放请求的队列
     */
    private Queue<Message> queue = new LinkedList<>();

    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    /**
     * 从 请求/消息 队列中获取消息
     * 假如队列中有消息，就取出返回，否则无限等待，等待其他线程唤醒
     * @return 返回消息
     */
    public synchronized Message getMessage() {
        // 守护方法
        while (queue.peek() == null) {
            // 队列中没有Message，需要去等待
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        // 队列中有Message，拿出放回
        return queue.poll();
    }

    /**
     * 带超时时间的获取消息的请求，等待指定时间
     * @return
     */
    public synchronized Message getMessage(long millis) {
        long startTime = System.currentTimeMillis(); // 获取调用方法的时间
        long wasteTime = 0; // 记录已消耗的时间
        // 守护方法
        while (queue.peek() == null) {
            // 队列中没有Message，需要去等待
            try {
                long delay = millis - wasteTime; // 计算需要睡眠的时间
                if (delay <= 0) {
                    break;
                }
                // 睡眠指定时间
                this.wait(delay);
                // 计算已经耗时时间
                wasteTime = System.currentTimeMillis() - startTime;
            } catch (InterruptedException ignored) {
            }
        }
        // 队列中有Message，拿出放回
        return queue.poll();
    }

    /**
     * 放入 请求/消息 至队列，唤醒等待的线程
     */
    public synchronized void putMessage(Message message) {
        queue.offer(message);
        this.notify();
    }

}
