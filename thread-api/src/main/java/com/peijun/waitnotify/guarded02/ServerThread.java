package com.peijun.waitnotify.guarded02;

import com.peijun.waitnotify.guardedsuspension.Request;
import com.peijun.waitnotify.guardedsuspension.RequestQueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:44
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class ServerThread extends Thread {
    private final Random random;
    // 此线程对应的守护对象的ID
    private final int guardedId;

    public ServerThread(int guardedId, String name) {
        super(name);
        this.guardedId = guardedId;
        this.random = new Random();
    }

    @Override
    public void run() {
        // 开始计算结果
        try {
            // 模拟计算耗时
            int time = random.nextInt(5) + 1;
            TimeUnit.SECONDS.sleep(time);
            // 添加计算结果
            GuardedObject guardedObject = DecoupleTask.getGuardedObject(guardedId);
            String result = "【result-守护ID-" + guardedObject.getGuardedId() + "】";
            guardedObject.putResult(result);
            System.out.println("--< 生产线程 >-- ：耗时" + time + "传递计算结果 ---> " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
