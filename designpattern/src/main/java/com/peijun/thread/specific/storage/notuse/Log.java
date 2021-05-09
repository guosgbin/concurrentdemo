package com.peijun.thread.specific.storage.notuse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/9 13:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Log {
    private static PrintWriter writer = null;

    static {
        try {
            writer = new PrintWriter(new FileWriter("log.txt"));
        } catch (IOException ignored) {

        }
    }

    // 写日志
    public static void println(String s) {
        writer.println(s);
    }

    // 关闭日志
    public static void close() {
        writer.println("==== End of log");
        writer.close();
    }
}
