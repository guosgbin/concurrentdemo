package com.peijun.synckey.feature;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 15:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ReentryDemo {
    public static void main(String[] args) {
        ReentryDemo reentry = new ReentryDemo();
        reentry.entry(reentry);
    }

    public void entry(ReentryDemo reentry) {
        synchronized (this) {
            System.out.println("我进来了");
            reentry.reentry();
        }
    }

    public void reentry() {
        synchronized (this) {
            System.out.println("我又进来了，来打我啊笨蛋");
        }
    }
}
