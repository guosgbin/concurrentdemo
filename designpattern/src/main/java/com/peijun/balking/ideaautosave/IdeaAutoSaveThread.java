package com.peijun.balking.ideaautosave;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 23:25
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class IdeaAutoSaveThread extends Thread{
    private final CodeFile codeFile;

    public IdeaAutoSaveThread(String name, CodeFile codeFile) {
        super(name);
        this.codeFile = codeFile;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                TimeUnit.SECONDS.sleep(3); // 3秒钟自动保存一次
                codeFile.saveCode(); // 显示地保存
            }
        } catch (Exception ignored) {
        }
    }
}
