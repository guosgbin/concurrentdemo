package com.peijun.workerthread.demo01;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/26 23:17
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class WorkerThread extends Thread{
    /**
     * 存放请求和线程池的对象
     */
    private final Channel channel;

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            Request request = channel.takeRequest();
            request.execute();
        }
    }
}
