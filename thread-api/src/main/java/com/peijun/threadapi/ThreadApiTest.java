package com.peijun.threadapi;

import org.junit.Test;

public class ThreadApiTest {

    /**
     * 获取当前执行线程的引用
     */
    @Test
    public void testCurrentThread() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getState());
        System.out.println(thread.getName());
    }
}
