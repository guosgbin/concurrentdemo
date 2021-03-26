package com.peijun.threadgroupapi.demo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ThreadCopy {
    /**
     *           parentGroup
     *               |
     *       |----|----|--------|
     *    线程1  线程2  线程3 sonGroup
     *                         |
     *                        线程4
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup parentGroup = new ThreadGroup("ParentGroup");
        init(parentGroup); // 初始化线程组和线程关系
        Thread[] threads = new Thread[parentGroup.activeCount()];
        int enumerate = parentGroup.enumerate(threads, false);
        System.out.println(Arrays.toString(threads));
    }

    private static void init(ThreadGroup parentGroup) throws InterruptedException {
        Runnable task = () -> {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
            }
        };
        // 显式指定新建的线程组的父级线程组
        ThreadGroup sonGroup = new ThreadGroup(parentGroup, "SonGroup");
        Thread t1 = new Thread(parentGroup, task,  "线程1");
        Thread t2 = new Thread(parentGroup, task, "线程2");
        Thread t3 = new Thread(parentGroup, task, "线程3");
        Thread t4 = new Thread(sonGroup, task, "线程4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        TimeUnit.SECONDS.sleep(1);
    }
}
