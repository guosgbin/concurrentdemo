package com.peijun.waitnotify.guardedsuspension;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:34
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * guarded 类
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                '}';
    }
}
