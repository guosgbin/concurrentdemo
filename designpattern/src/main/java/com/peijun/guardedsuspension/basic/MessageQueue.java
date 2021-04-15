package com.peijun.guardedsuspension.basic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:36
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * MessageQueue 用于存放Message请求类
 *
 * {@link #getMessage()} 会获取最先存放在MessageQueue中的一个请求，作为其返回值
 * 如果一个请求Message对象都没有，那就一直等待，直到某个线程执行{@link #putMessage(Message)}
 *
 * {@link #putMessage(Message)} 用于添加一个请求。
 * 当线程想要向MessageQueue中添加一个Message实例时，可以调用该方法
 *
 * 这两个方法都上锁了，这样针对一个MessageQueue队列，只允许一个线程去写操作
 */
public class MessageQueue {
    private final Queue<Message> queue = new LinkedList<>();

    /**
     *  会获取最先存放在RequestQueue中的一个请求，作为其返回值
     *  如果一个请求Request对象都没有，那就一直等待，直到某个线程执行{@link #putMessage(Message)}
     */
    public synchronized Message getMessage() {
        while (queue.peek() == null) {
            // 队列为空，则等待
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        return queue.remove();
    }

    /**
     *  用于添加一个请求。
     *  当线程想要向RequestQueue中添加一个Request实例时，可以调用该方法
     *  唤醒等待线程
     */
    public synchronized void putMessage(Message message) {
        queue.offer(message);
        this.notifyAll();
    }
}
