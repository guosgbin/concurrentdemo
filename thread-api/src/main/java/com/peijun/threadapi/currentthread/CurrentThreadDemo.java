package com.peijun.threadapi.currentthread;

/**
 * 测试currentThread()
 */
public class CurrentThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            System.out.println("子线程执行中...");
            System.out.println("当前执行的线程是:" + Thread.currentThread().getName());
            }, "线程一百");

        t1.start();
        t1.join();
        System.out.println("主线程执行中...");
        System.out.println("当前执行的线程是: " + Thread.currentThread().getName());
    }
}
