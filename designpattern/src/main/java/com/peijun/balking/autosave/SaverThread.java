package com.peijun.balking.autosave;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 8:46
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 用于定期保存数据内容
 * 大约每隔1秒钟就调用依次data对象的save方法去自动保存
 */
public class SaverThread extends Thread{
    private final Data data;

    public SaverThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true) {
                data.save(); // 要求保存数据
                TimeUnit.SECONDS.sleep(3); // 睡眠约1秒
            }
        } catch (Exception ignored) {
        }
    }
}
