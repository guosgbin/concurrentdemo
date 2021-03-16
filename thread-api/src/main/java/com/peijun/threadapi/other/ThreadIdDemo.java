package com.peijun.threadapi.other;

/**
 * 获取线程ID
 */
public class ThreadIdDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("你好啊"));
        System.out.println("子线程的ID --> " + t1.getId());
        System.out.println("主线程的ID --> " + Thread.currentThread().getId());
    }
}
