package com.peijun.readwritelock.lock2;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/24 23:23
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 表示对ShareData实例执行写入操作的线程，
 * 构造函数参数filler是一个字符串，程序会逐个取出该字符串中的字符，
 * 并write到ShareData的实例中。每写入一次，线程就会在0-3秒内随机睡眠一段时间，
 * 此外nextchar方法用于获取下一次应该写入的字符
 */
public class WriterThread extends Thread{
    private static final Random random = new Random();
    private final ShareData shareData;
    private final String text;
    private int index = 0;

    public WriterThread(ShareData shareData, String text) {
        this.shareData = shareData;
        this.text = text;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextchar();
                shareData.write(c);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(3000));
            }
        } catch (InterruptedException ignored) {
        }
    }

    private char nextchar() {
        char c = text.charAt(index);
        index++;
        if (index >= text.length()) {
            index = 0;
        }
        return c;
    }
}
