package com.peijun.balking.checkone;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/16 8:37
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 模拟 系统全局初始化一次配置文件
 */
public class InitService{
    // 用来表示是有线程已经初始化配置了
    private volatile boolean isInit;

    /**
     * 加载配置
     * @return false加载失败，true加载成功
     */
    public synchronized boolean initConfig() {
        try {
            if (isInit) {
                System.out.println("配置已经加载过了，无需重新加载");
                return false;
            }
            // 模拟加载配置操作
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " 加载配置了");
            // 加载完后，需要将isInit状态设置为true，表示已经加载过
            isInit = true;
            return true;
        } catch (InterruptedException ignored) {
            return false;
        }
    }

    public static void main(String[] args) {
        InitService initService = new InitService();
        for (int i = 0; i < 10; i++) {
            new Thread(initService::initConfig).start();
        }
    }
}
