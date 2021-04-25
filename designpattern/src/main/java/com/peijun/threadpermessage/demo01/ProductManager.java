package com.peijun.threadpermessage.demo01;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/25 23:14
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ProductManager {
    public void call(String business) {
        Programmer programmer = new Programmer(new Request(business));
        new Thread(programmer).start();
    }
}
