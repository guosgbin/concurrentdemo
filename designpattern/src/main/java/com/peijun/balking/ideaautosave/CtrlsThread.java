package com.peijun.balking.ideaautosave;

import com.peijun.balking.autosave.Data;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 23:22
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 码农自动保存线程
 */
public class CtrlsThread extends Thread{
    private final CodeFile codeFile;

    public CtrlsThread(String name, CodeFile codeFile) {
        super(name);
        this.codeFile = codeFile;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                codeFile.changeCode("HelloWorld." + i); // 修改数据
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1500)); // 写代码中...
                codeFile.saveCode(); // 显示地保存
            }
        } catch (Exception ignored) {
        }
    }
}
