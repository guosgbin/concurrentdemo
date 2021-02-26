package com.peijun.volatilekey.application;

import java.util.concurrent.TimeUnit;

public class VolatileTrigger02 implements Runnable{
    private volatile boolean started = true;

    public void run() {
        while (started) {
            // 类似发送心跳的操作
        }
    }

    public void shutdownHeartBeats() {
        this.started = false;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTrigger02 task = new VolatileTrigger02();
        Thread t1 = new Thread(task);
        t1.start();
        // 运行几秒后 关掉这个进程
        TimeUnit.SECONDS.sleep(3);
        task.shutdownHeartBeats();
    }
}

