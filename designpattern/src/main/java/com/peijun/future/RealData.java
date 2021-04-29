package com.peijun.future;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/28 21:53
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class RealData {
    private String taskDesc;
    private Random random = new Random();

    public RealData(String taskDesc) {
        try {
            System.out.println("         开始加工..");
            this.taskDesc = taskDesc + "=我是RealData加工后的";
            TimeUnit.SECONDS.sleep(random.nextInt(5)); // 随机睡眠
            System.out.println("         加工完毕");
        } catch (InterruptedException ignored) {
        }
    }

    public String get() {
        return taskDesc;
    }
}
