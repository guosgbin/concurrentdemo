package com.peijun.singleThreadedExcution.notuse;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/11 23:18
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("测试开始...");
        Gate gate = new Gate();
        new People(gate, "黄兴", "黄冈").start();
        new People(gate, "武扬", "武汉").start();
        new People(gate, "荆轲", "荆州").start();
    }
}
