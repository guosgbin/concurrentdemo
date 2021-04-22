package com.peijun.producerconsumer;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/22 22:09
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 表示木材类 就是生产者消费者模式中间传递的对象
 */
public class Lumber {
    private String name;

    public Lumber(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
