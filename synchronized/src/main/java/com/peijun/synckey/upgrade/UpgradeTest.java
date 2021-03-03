package com.peijun.synckey.upgrade;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/3 8:47
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 锁升级 过程测试
 */
public class UpgradeTest {

    public static void main(String[] args) throws InterruptedException {
//        testNoLock();
//        testBiasedLock();
        testBiasedLockAndHashcode();

    }

    /**
     * 无锁状态
     */
    public static void testNoLock() {
        Object model2 = new Object();
        System.out.println(ClassLayout.parseInstance(model2).toPrintable());
        System.out.println("hash: -----" + model2.hashCode());
        // 偏向锁
        System.out.println(ClassLayout.parseInstance(model2).toPrintable());
//        binaryToDecimal(model2.hashCode());
    }

    /**
     * 偏向锁
     */
    public static void testBiasedLock() throws InterruptedException {
        // JVM 启动 5 秒后创建对象
        // 5秒是因为 	-XX:BiasedLockingStartupDelay=4000 默认4秒后 开启偏向锁
        Thread.sleep(5000);
        Object object = new Object();
        System.out.println(ClassLayout.parseInstance(object).toPrintable());
        // 偏向锁
        // 当一个对象已经计算过identity hash code，它就无法进入偏向锁状态；
        // 当一个对象当前正处于偏向锁状态，并且需要计算其identity hash code的话，则它的偏向锁会被撤销。
        synchronized (object) {
            System.out.println(ClassLayout.parseInstance(object).toPrintable());
        }
    }

    /**
     * 偏向锁和哈希码
     */
    public static void testBiasedLockAndHashcode() throws InterruptedException {
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        Object model2 = new Object();
        System.out.println(ClassLayout.parseInstance(model2).toPrintable());
        System.out.println("hash: -----" + model2.hashCode());
//        System.out.println("hashcode:: "+Integer.toBinaryString(model2.hashCode()));
        System.out.println("----------after hashcode----------");
        System.out.println(ClassLayout.parseInstance(model2).toPrintable());
        synchronized (model2) {
            System.out.println("---------after lock-------");
            model2.hashCode();
            System.out.println(ClassLayout.parseInstance(model2).toPrintable());
        }
    }
}


