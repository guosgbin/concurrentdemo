package com.peijun.balking.ideaautosave;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 23:08
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 代码文件
 */
public class CodeFile {
    // 假如代码发生变化，change条件就为true，反之为false
    private volatile boolean change = true;
    // 新写的代码，其实就是
    private String code;
    // 保存文件的路径
    private final String filename;
    // 输出流
    private Writer writer;

    public CodeFile(String code, String filename) {
        this.code = code;
        this.filename = filename;
    }

    /**
     * 改变守护对象的方法
     */
    public synchronized void changeCode(String newCode) {
        this.code = newCode; // 设置新的代码
        change = true; // 表明代码发生改变了
    }

    /**
     * 守护方法 若数据内容修改过，则保存到文件中
     */
    public synchronized void saveCode() throws IOException {
        // 守护条件
        if (!change) {
            System.out.println(Thread.currentThread().getName() + " has been saved, do nothing...");
            return;
        }
        // 假如doSave()方法抛出异常，此时直接向上抛异常了，
        // 下面的change=false就不会执行，仍然为true，表明数据内容和文件内容是不一致的
        doSave();
        change = false; // 恢复状态为false，表示此时没有状态发生改变了
    }

    /**
     * 真正的保存方法
     */
    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " 调用保存方法，保存的内容为 = " + code);
        writer = new FileWriter(filename);
        writer.write(code);
        writer.flush();
        writer.close();
    }
}
