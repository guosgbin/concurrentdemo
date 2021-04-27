package com.peijun.workerthread.demo01;

import java.util.*;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/26 22:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 通道类
 * 用于创建并保存Worker线程的类 其实就是丐版线程池
 */
public class Channel {
    /**
     * 最大请求缓存大小
     */
    private final int maxRequest;

    /**
     * 请求队列
     */
    private final Queue<Request> requestQueue = new LinkedList<>();

    /**
     * 线程池
     */
    private WorkerThread[] threadPool;

    /**
     * 指定线程池大小，最大的请求缓存个数
     */
    public Channel(int maxRequest, int threads) {
        this.maxRequest = maxRequest;
        this.threadPool = new WorkerThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }

    /**
     * 启动所有线程
     */
    public void preStartThread() {
        Arrays.stream(threadPool).forEach(Thread::start);
    }

    /**
     * 存放传入的请求到任务队列中
     */
    public synchronized void putRequest(Request request) {
        // 守护条件
        while (requestQueue.size() >= maxRequest) {
            try {
                // 任务队列满了，等待
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        // 任务队列未满，任务入队
        requestQueue.offer(request);
        // 唤醒在takeRequest等待的线程
        this.notifyAll();
    }

    /**
     * 从任务队列中获取任务去执行
     */
    public synchronized Request takeRequest() {
        // 守护条件
        while (requestQueue.peek() == null) {
            try {
                // 任务队列没有任务，等待
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        // 队列有任务
        Request request = requestQueue.poll();
        // 唤醒在putRequest等待的线程
        this.notifyAll();
        return request;
    }
}
