package com.peijun.balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 8:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 表示可以修改并保存的数据的类
 */
public class Data {
    // 保存的文件名称
    private final String filename;
    // 数据内容
    private String content;
    // 修改后的内容若未保存，则为true
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    // 修改数据内容
    public synchronized void chenge(String newContent) {
        content = newContent;
        changed = true;
    }

    // 若数据内容修改过，则保存到文件中
    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();
        changed = false;
    }

    // 将数据内容实际保存到文件中
    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " 调用保存方法，保存的内容为 = " + content);
        Writer writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
}
