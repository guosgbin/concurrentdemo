package com.peijun.singlethreadedexcution.solveproblem;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/12 22:24
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 一组对象
 */
public class Pair<F,S> {
    private F firstData;
    private S secondData;

    public Pair(F firstData, S secondData) {
        this.firstData = firstData;
        this.secondData = secondData;
    }
}
