package com.peijun.waitnotify.guarded02;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/27 0:17
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 解耦 生产结果线程和等待结果线程
 */
public class DecoupleTask {
    // 存放守护对象的Map,key为守护对象ID,value为守护对象
    private static ConcurrentHashMap<Integer, GuardedObject> guardedMap = new ConcurrentHashMap<>();
    // 自增ID
    private static AtomicInteger guardedId = new AtomicInteger(0);

    // 生成唯一ID
    private static Integer nextGuardedId() {
        return guardedId.incrementAndGet();
    }

    // 根据守护对象ID 获取守护对象
    public static GuardedObject getGuardedObject(Integer guardedId) {
        return guardedMap.remove(guardedId);
    }

    // 获取所有守护对象的ID
    public static Set<Integer> getAllGuardedObject() {
        return guardedMap.keySet();
    }

    // 创建守护对象
    public static GuardedObject createGuardedObject() {
        GuardedObject guarded = new GuardedObject();
        guarded.setGuardedId(nextGuardedId());
        guardedMap.put(guarded.getGuardedId(), guarded);
        return guarded;
    }
}
