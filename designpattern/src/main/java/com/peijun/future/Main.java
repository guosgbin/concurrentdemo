package com.peijun.future;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/28 21:52
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试类
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程开始...");

        Host host = new Host();
        Data data1 = host.request("产品1");
        Data data2 = host.request("产品2");
        Data data3 = host.request("产品3");

        System.out.println("主线程做其他事情开始...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程做其他事情结束...");

        System.out.println("data1 = " + data1.get());
        System.out.println("data2 = " + data2.get());
        System.out.println("data3 = " + data3.get());

        System.out.println("主线程结束...");
    }
}
