package com.peijun.singleThreadedExcution.solveproblem;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/12 22:02
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 代表人的线程类
 */
public class People<F, S> extends Thread {
    private final Pair<F, S> pair;

    public People(String name, Pair<F, S> pair) {
        super(name);
        this.pair = pair;
    }

    public void run() {
        while (true) {
            synchronized (pair) {
                System.out.println(getName() + "两个筷子都拿到了, 开吃...");
            }
        }
    }
}
