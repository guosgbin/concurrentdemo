package com.peijun.balking.autosave;

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
    // 数据内容，也就是写入文件的内容
    private String content;
    // 修改后的内容若未保存，则为true,fasle表示未修改过
    private boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    // 修改数据内容
    public synchronized void change(String newContent) {
        content = newContent; // 新的数据内容赋值给content字段
        changed = true; // 表示内容进行了修改
    }

    // 若数据内容修改过，则保存到文件中
    public synchronized void save() throws IOException {
        // 守护条件
        if (!changed) {
            System.out.println(Thread.currentThread().getName()+"文本没有修改啊,不做事了");
            return; // change为false，表示未修改，直接返回
        }
        // 实际执行文件保存的方法
        // 假如doSave()方法抛出异常，此时直接向上抛异常了，
        // 下面的change=false就不会执行，仍然为true，表明数据内容和文件内容是不一致的
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
