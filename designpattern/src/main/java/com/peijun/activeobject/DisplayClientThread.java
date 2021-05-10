package com.peijun.activeobject;

import com.peijun.activeobject.activeobject.ActiveObject;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:49
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class DisplayClientThread extends Thread {
    private final ActiveObject activeObject;

    public DisplayClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                // 没有返回值的调用
                String string = Thread.currentThread().getName() + " " + i;
                activeObject.displayString(string);
                TimeUnit.MILLISECONDS.sleep(200);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
