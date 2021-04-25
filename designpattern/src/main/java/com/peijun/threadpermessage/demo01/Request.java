package com.peijun.threadpermessage.demo01;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/25 23:07
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Request {

    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
