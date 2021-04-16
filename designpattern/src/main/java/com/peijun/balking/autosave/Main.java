package com.peijun.balking.autosave;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 8:51
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        Data data = new Data("data.txt" ,"(empty)");
        new ChangerThread("码农手动ctrl+s了", data).start();
        new SaverThread("IDEA自动保存线程", data).start();
    }
}
