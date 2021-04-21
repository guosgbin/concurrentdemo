package com.peijun.guardedsuspension.multiple;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/20 8:44
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 解耦类， 生产结果的线程和等待结果的线程
 */
public class DecoupleTask {
    // 存放守护对象的Map，key为守护对象MessageQueue的ID，value为守护对象
    private static ConcurrentHashMap<Integer, MessageQueue> queueMap = new ConcurrentHashMap<>();
    // 自增ID
    private static AtomicInteger queueId = new AtomicInteger(0);

    // 生成自增ID
    public static Integer nextQueueId() {
        return queueId.getAndIncrement();
    }

    // 根据守护对象的ID，获取守护对象
    public static MessageQueue getQueueById(Integer queueId) {
        return queueMap.remove(queueId);
    }

    // 获取所有守护对象的ID
    public static Set<Integer> getAllQueueId() {
        return queueMap.keySet();
    }

    // 创建MessageQueue对象的方法
    public static MessageQueue createQueue() {
        // 创建队列对象，并设置其对应的ID
        MessageQueue queue = new MessageQueue();
        queue.setQueueId(nextQueueId());
        // 添加到集合中
        queueMap.put(queue.getQueueId(), queue);
        return queue;
    }
}
