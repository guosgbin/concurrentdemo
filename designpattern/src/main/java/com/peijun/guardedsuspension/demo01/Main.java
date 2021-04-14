package com.peijun.guardedsuspension.demo01;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:46
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        new ClientThread(messageQueue, "康康").start();
        new ServerThread(messageQueue, "Bob").start();
    }
}
