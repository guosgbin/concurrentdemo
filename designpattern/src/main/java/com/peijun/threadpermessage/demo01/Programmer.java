package com.peijun.threadpermessage.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/25 23:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 程序员用于处理每一个提交的需求Request的请求，用于Programmer会被Thread执行，实现Runnable接口
 */
public class Programmer implements Runnable{
    /**
     * 需要处理的Request需求
     */
    private final Request request;

    public Programmer(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始处理 " + request);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("处理结束 " + request);
        } catch (InterruptedException ignored) {
        }
    }
}
