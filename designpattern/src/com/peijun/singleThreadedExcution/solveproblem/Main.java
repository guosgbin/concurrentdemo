package com.peijun.singleThreadedExcution.solveproblem;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/12 22:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 现象 卡主了 发生死锁了
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 创建两根筷子出来 这两根筷子就代表多个共享资源
        Chopsticks stick1 = new Chopsticks("筷子1");
        Chopsticks stick2 = new Chopsticks("筷子2");

        // 创建两个人出来
        new People("Alice", new Pair<>(stick1, stick2)).start();
        new People("Bob", new Pair<>(stick2, stick1)).start();
    }
}
