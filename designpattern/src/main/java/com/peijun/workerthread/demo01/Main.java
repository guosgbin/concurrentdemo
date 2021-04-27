package com.peijun.workerthread.demo01;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/27 21:59
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        // 创建Channel类，用来缓存请求队列和线程
        Channel channel = new Channel(5,5);
        // 启动线程池中的所有线程
        channel.preStartThread();
        // 创建3个线程去添加请求到请求队列
        new ClientThread("田架", channel).start();
        new ClientThread("李经理", channel).start();
        new ClientThread("谭老师", channel).start();
    }
}
