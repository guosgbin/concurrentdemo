package com.peijun.threadspecificstorage.use;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/9 14:31
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class TSLog {
    private PrintWriter writer = null;

    // 初始化writer字段
    public TSLog(String filename) {
        try {
            writer = new PrintWriter((new FileWriter(filename)));
        } catch (IOException ignored) {
        }
    }

    // 写日志
    public void println(String s) {
        writer.println(s);
    }

    public void close() {
        writer.println("==== End of log ====");
        writer.close();
    }

}
