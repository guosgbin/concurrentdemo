package com.peijun.waitnotify.guarded02;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/27 15:57
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 搞5个线程去等待其他线程的结果
        for (int i = 1; i <= 3; i++) {
            new ClientThread().start();
        }

        TimeUnit.MILLISECONDS.sleep(10);

        // 去DecoupleTask类中获取 正在等待的线程 去生产结果
        Set<Integer> guardedSet = DecoupleTask.getAllGuardedObject();
        for (Integer guardedId : guardedSet) {
            new ServerThread(guardedId, "生产线程" + guardedId).start();
        }


    }
}
