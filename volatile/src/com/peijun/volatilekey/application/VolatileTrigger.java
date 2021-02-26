package com.peijun.volatilekey.application;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/26 16:36
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * volatile 触发器 应用
 */
public class VolatileTrigger {

    private static  boolean is_init = false;
    static Logger logger =Logger.getLogger("212121");

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!is_init) {
                // 初始化配置完成, 执行任务
                logger.info("666");
            }
            System.out.println("任务执行完毕");
        });


        // 假设是做配置操作
        Thread t2 = new Thread(() -> {
            is_init = true;
            System.out.println("初始化配置完成...");
        });

        t1.start();

        TimeUnit.MICROSECONDS.sleep(2);
        t2.start();
    }
}
