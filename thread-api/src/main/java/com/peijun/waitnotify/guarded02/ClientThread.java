package com.peijun.waitnotify.guarded02;

import com.peijun.waitnotify.guardedsuspension.Request;
import com.peijun.waitnotify.guardedsuspension.RequestQueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ClientThread extends Thread {

    @Override
    public void run() {
        // 接受其他线程的计算结果
        GuardedObject guardedObject = DecoupleTask.createGuardedObject();
        System.out.println("--< 等待线程 " + Thread.currentThread().getName()
                + ">-- ：开始等待守护ID-" + guardedObject.getGuardedId() + " 的计算结果...");
        Object result = guardedObject.getResult(5000);
        if (result == null) {
            System.out.println("--< 等待线程 " + Thread.currentThread().getName() + ">-- ：等个锤子, 太慢了, 不等了...");
        } else {
            System.out.println("--< 等待线程后 " + Thread.currentThread().getName()
                    + ">-- ：获得守护ID-" + guardedObject.getGuardedId() + " 的计算结果 ---> " + result);

        }
    }
}
