package com.peijun.waitnotify.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:36
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class RequestQueue {
    private final Queue<Request> queue = new LinkedList<>();

    /**
     * 获取 请求
     */
    public synchronized Request getRequest() {
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
     * 添加请求 唤醒等待线程
     */
    public synchronized void putRequest(Request request) {
        queue.offer(request);
        this.notifyAll();
    }
}
