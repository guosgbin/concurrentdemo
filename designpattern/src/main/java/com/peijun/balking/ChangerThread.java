package com.peijun.balking;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 8:48
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 用于修改数据内容，并执行保存处理
 */
public class ChangerThread extends Thread{
    private final Data data;
    private final Random random = new Random();

    public ChangerThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                data.chenge("No." + i); // 修改数据
                TimeUnit.MILLISECONDS.sleep(random.nextInt(1000)); // 执行其他操作
                data.save(); // 显示地保存
            }
        } catch (Exception ignored) {
        }
    }
}
