package com.peijun.balking;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/15 8:51
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        Data data = new Data("data.txt" ,"(empty)");
        new ChangerThread("ChangerThread", data).start();
        new SaverThread("SaverThread", data).start();
    }
}
