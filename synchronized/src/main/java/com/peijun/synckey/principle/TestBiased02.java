package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 测试偏向锁批量撤销
 */
public class TestBiased02 {

    static Thread t1;
    static Thread t2;
    static Thread t3;
    static int loopFlag = 40;

    public static void main(String[] args) {
        final List<Object> list = new ArrayList<>();
        t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < loopFlag; i++) {
                    Object a = new Object();
                    list.add(a);
                    System.out.println("加锁前" + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                    synchronized (a) {
                        System.out.println("加锁中" + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                    }
                    System.out.println("加锁结束" + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                }
                System.out.println("============t1 都是偏向锁=============");
                //防止竞争 执行完后叫醒  t2
                LockSupport.unpark(t2);
            }
        };
        t2 = new Thread() {
            @Override
            public void run() {
                //防止竞争 先睡眠t2
                LockSupport.park();
                for (int i = 0; i < loopFlag; i++) {
                    Object a = list.get(i);
                    //因为从list当中拿出都是偏向t1
                    System.out.println("加锁前" + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                    synchronized (a) {
                        //前20撤销偏向t1；然后升级轻量指向t2线程栈当中的锁记录
                        //后面的发送批量偏向t2
                        System.out.println("加锁中 " + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                    }
                    //因为前20是轻量，释放之后为无锁不可偏向
                    //但是后面的是偏向t2 释放之后依然是偏向t2
                    System.out.println("加锁结束" + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                }
                System.out.println("新产生的对象" + ClassLayout.parseInstance(new  Object()).toPrintable());
                LockSupport.unpark(t3);
            }
        };
        t3 = new Thread() {
            @Override
            public void run() {
                //防止竞争 先睡眠t2
                LockSupport.park();
                for (int i = 0; i < loopFlag; i++) {
                    Object a = list.get(i);
                    System.out.println("加锁前" + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                    synchronized (a) {
                        System.out.println("加锁中 " + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                    }
                    System.out.println("加锁结束" + (i + 1) + "次" + " " + ClassLayout.parseInstance(a).toPrintable());
                }
                System.out.println("新产生的对象" + ClassLayout.parseInstance(new  Object()).toPrintable());
            }
        };
        t1.start();
        t2.start();
        t3.start();
    }

}

