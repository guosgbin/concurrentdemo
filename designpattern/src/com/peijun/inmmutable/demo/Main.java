package com.peijun.inmmutable.demo;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/13 8:18
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试类
 */
public class Main {
    public static void main(String[] args) {
        Person haung = new Person("黄兴", "黄冈");
        Person wu = new Person("武扬", "武汉");
        new PersonPrint(haung).start();
        new PersonPrint(wu).start();
        new PersonPrint(wu).start();
    }
}
