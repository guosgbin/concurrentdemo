package com.peijun.twophasetermination;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/8 8:39
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class MonitorThread extends Thread{
    /**
     * 是否是关闭状态
     */
    private volatile boolean isShutdown = false;

    /**
     * 接收终止的请求
     */
    public void shutdown() {
        isShutdown = true;
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            while (!isShutdown) {
                doWork();
            }
        } catch (InterruptedException ignored) {
        } finally {
            doShutdown();
        }
    }

    private void doWork() throws InterruptedException {
        System.out.println("我正在监视系统啊...");
        TimeUnit.SECONDS.sleep(1);
    }

    /**
     * 处理终止的操作
     */
    private void doShutdown() {
        System.out.println("线程料理后事，料理完后就Terminate了");
    }
}
