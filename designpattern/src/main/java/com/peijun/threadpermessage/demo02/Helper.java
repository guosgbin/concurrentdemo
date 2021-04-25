package com.peijun.threadpermessage.demo02;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/25 23:29
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Helper {
    public void handle(int count, char c) {
        try {
            System.out.println("       开始 处理(" + count + ", " + c + ")");
            for (int i = 0; i < count; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.print(c);
            }
            System.out.println();
            System.out.println("       结束 处理(" + count + ", " + c + ")");
        } catch (InterruptedException ignored) {
        }
    }
}
