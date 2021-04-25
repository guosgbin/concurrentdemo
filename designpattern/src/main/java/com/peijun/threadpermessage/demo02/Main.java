package com.peijun.threadpermessage.demo02;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/25 23:29
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("main方法开始...");
        Host host = new Host();
        host.request(10, 'A');
        host.request(20, 'B');
        host.request(30, 'C');
        System.out.println("main方结束...");
    }
}
