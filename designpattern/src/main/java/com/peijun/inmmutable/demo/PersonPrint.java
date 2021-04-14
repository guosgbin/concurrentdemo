package com.peijun.inmmutable.demo;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/13 8:11
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class PersonPrint extends Thread {
    // 组合 人 的对象
    private Person person;
    public PersonPrint(Person person) {
        this.person = person;
    }

    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " 打印 " + person);
        }
    }
}
