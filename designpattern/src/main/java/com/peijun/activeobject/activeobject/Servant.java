package com.peijun.activeobject.activeobject;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:38
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Servant implements ActiveObject {
    @Override
    public Result<String> makeString(int count, char fillchar) {
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = fillchar;
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        return new RealResult<String>(new String(buffer));
    }


    @Override
    public void displayString(String string) {
        try {
            System.out.println("displayString:" + string);
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException ignored) {
        }
    }
}
