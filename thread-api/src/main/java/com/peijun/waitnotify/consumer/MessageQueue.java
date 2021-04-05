package com.peijun.waitnotify.consumer;

import java.util.LinkedList;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/5 22:39
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 消息队列
 */
public class MessageQueue {
    // 消息队列
    private LinkedList<Message> queue = new LinkedList<>();
    // 消息队列运行最大容量
    private int capcity;

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    /**
     * 从消息队列获取消息
     */
    public Message take() {
        synchronized (queue) {
            // 当队列为空时，等待put去唤醒
            while (queue.peek() == null) {
                try {
                    System.out.println("消息队列为空，等待生产中...");
                    queue.wait();
                } catch (InterruptedException ignored) {
                }
            }
            // 从队列的头部获取消息返回
            Message message = queue.poll();
            // 唤醒put中因队列满而等待的线程
            queue.notifyAll();
            System.out.println("消费一个结果");
            return message;
        }
    }

    /**
     * 向队列中添加消息
     */
    public void put(Message message) {
        synchronized (queue) {
            // 当队列已经满时，等待take去唤醒
            while (queue.size() == capcity) {
                try {
                    System.out.println("消息队列满了，等待消费中...");
                    queue.wait();
                } catch (InterruptedException ignored) {
                }
            }
            queue.push(message);
            // 唤醒take中因队列为空而等待的线程
            queue.notifyAll();
            System.out.println("生产一个结果");
        }
    }
}
