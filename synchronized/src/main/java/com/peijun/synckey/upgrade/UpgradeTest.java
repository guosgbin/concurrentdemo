package com.peijun.synckey.upgrade;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/3 8:47
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 锁升级 过程测试
 * <p>
 * 无锁状态
 * 无锁 到 偏向锁
 * 偏向锁 -> 当一个对象已经计算过哈希值 无法进入偏向锁
 * 当一个对象已经是偏向锁状态，锁状态会被消除为无锁状态
 */
public class UpgradeTest {

    Object monitor = new Object();

    /**
     * 测试  无锁状态
     */
    @Test
    public void testNoLock() {
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
//        System.out.println("=====锁对象的哈希值===== " + monitor.hashCode());
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
    }

    /**
     * 测试  无锁 -> 偏向锁
     * 开启偏向锁 -> 当仅仅一个线程来拿锁  就是偏向锁
     * 关闭偏向锁 -> 当仅仅一个线程来拿锁  直接变成轻量锁了
     * <p>
     * -XX:BiasedLockingStartupDelay=0 设置偏向锁延迟时间
     * -XX:-UseBiasedLocking=false 关闭偏向锁 默认会进入轻量级锁状态
     */
    @Test
    public void testBiasedLock() throws InterruptedException {
        // 5秒是因为 	-XX:BiasedLockingStartupDelay=4000 默认4秒后 开启偏向锁
//        TimeUnit.SECONDS.sleep(5);
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
//        System.out.println(monitor.hashCode());
        // 偏向锁
        for (int i = 1; i < 4; i++) {
            synchronized (monitor) {
                System.out.printf("=====一个线程获取锁之后 %s -> 偏向锁状态=====%n", i);
//                monitor.hashCode(); // 获取锁对象的哈希值  这个注释 打开 会直接变为重量级锁
                System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

    /**
     * 偏向锁和哈希码
     * 当一个对象已经计算过 identity hash code，它就无法进入偏向锁状态
     * 当一个对象当前正处于偏向锁状态，并且需要计算其identity hash code的话，则它的偏向锁会被撤销。
     * <p>
     * -XX:BiasedLockingStartupDelay=0 设置偏向锁延迟时间
     */
    @Test
    public void testCaclHashBiasedLock() throws InterruptedException {
        System.out.println("=====锁对象初始状态 -> 偏向状态=====");
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
        System.out.println("=====锁对象的哈希值===== : " + monitor.hashCode());
        System.out.println("=====计算哈希值之后 此时偏向锁被撤销了 -> 无锁状态 =====");
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
        for (int i = 1; i < 4; i++) {
            synchronized (monitor) {
                System.out.printf("=====获取锁之后 %s -> 轻量锁状态=====%n", i);
//                monitor.hashCode(); // 获取锁对象的哈希值
                System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }

    /**
     * https://www.cnblogs.com/yuhangwang/p/11295940.html
     * <p>
     * befor lock：绿颜色表示：虽然是偏向锁，但是黄颜色表示没有任何线程持有锁（一个对象被初始化的时候是可偏向的）
     * lock  ing： 绿颜色表示偏向锁，黄颜色的表示当前线程拿到锁
     * after lock：绿颜色表示偏向锁，黄颜色的表示当前线程拿到锁，还是偏向的状态；（偏向锁退出锁后依然是偏向状态）
     * <p>
     * jvm在初始化一个对象的时候，
     * 如果没有启用偏向锁延迟，就会去判断这个对象是否可以被偏向，如果可以就是偏向锁；
     * 退出同步代码块 还是偏向锁
     */
    @Test
    public void testNoCaclHash() {
        //hash计算？
        //Object.hashCode();

        System.out.println("获取锁之前...");
        //无锁：偏向锁？
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());

        synchronized (monitor) {
            System.out.println("同步代码中...");
            System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
        }

        System.out.println("获取锁之后...");
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
    }

    /**
     * https://www.cnblogs.com/yuhangwang/p/11295940.html
     * <p>
     * 结果显示并不是偏向锁了，说明对象在计算过hashcode之后就不能被偏向；
     * <p>
     * 具体来说，在线程进行加锁时，如果该锁对象支持偏向锁，那么 Java 虚拟机会通过 CAS操作，
     * 将当前线程的地址记录在锁对象的标记字段之中，并且将标记字段的最后三位设置为：1 01；
     * <p>
     * 在接下来的运行过程中，每当有线程请求这把锁，Java 虚拟机只需判断锁对象标记字段中：
     * 最后三位是否为： 1 01，是否包含当前线程的地址，以及 epoch 值是否和锁对象的类的epoch 值相同。
     * 如果都满足，那么当前线程持有该偏向锁，可以直接返回；
     */
    @Test
    public void testCaclHash() {
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
        //hash计算？
        monitor.hashCode();

        System.out.println("获取锁之前...");
        //无锁：偏向锁？
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());

        synchronized (monitor) {
            System.out.println("同步代码中...");
            System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
        }

        System.out.println("获取锁之后...");
        System.out.println(ClassLayout.parseInstance(monitor).toPrintable());
    }


    /**
     * 仅证明批量重偏向
     * <p>
     * 批量重偏向 案例一
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        // 搞100个锁对象
        List<Object> monitorList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            monitorList.add(new Object());
        }
        Thread t1 = new Thread(() -> {
            System.out.println("加锁前 get(0) 应该是无锁可偏向 " + ClassLayout.parseInstance(monitorList.get(0)).toPrintable());
            for (Object monitor : monitorList) {
                // 挨个加锁
                synchronized (monitor) {
                    System.out.print("加锁 >");
                }
            }
            System.out.println();
            System.out.println("加锁后 get(0) 应该是偏向锁" + ClassLayout.parseInstance(monitorList.get(0)).toPrintable());
            try {
                TimeUnit.SECONDS.sleep(1000);//这里不让线程死，防止线程ID复用
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
//        t1.join();

        TimeUnit.SECONDS.sleep(5);
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                Object monitor = monitorList.get(i);
                synchronized (monitor) {
                    System.out.print("加锁 >");
                }
                if (i == 18) {
                    System.out.println();
                    System.out.println("加锁后 get(18) 应该是无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                }
                if (i == 19) { //开始重偏向
                    System.out.println();
                    System.out.println("加锁后 get(19) 应该是偏向锁 " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                    System.out.println("加锁后 get(0) 应该是无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(0)).toPrintable());
                    System.out.println("加锁后 get(99) 应该是偏向锁 偏向t1 " + ClassLayout.parseInstance(monitorList.get(99)).toPrintable());
                }
                if (i == 20) {
                    System.out.println();
                    System.out.println("加锁后 get(20) 应该是偏向锁 " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                }
            }
        });
        t2.start();
        t2.join();
    }

    /**
     * 批量重偏向
     * 案例2
     */
    @Test
    public void test02() throws InterruptedException {
        List<Object> monitorList = new ArrayList<>();
        //初始化数据
        for (int i = 0; i < 100; i++) {
            monitorList.add(new Object());
        }

        Thread t1 = new Thread() {
            String name = "1";

            public void run() {
                System.out.printf(name);
                for (Object a : monitorList) {
                    synchronized (a) {
                        if (a == monitorList.get(10)) {
                            System.out.println("t1 预期是偏向锁" + 10 + ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("main 预期是偏向锁" + 10 + ClassLayout.parseInstance(monitorList.get(10)).toPrintable());

        Thread t2 = new Thread() {
            String name = "2";

            public void run() {
                System.out.printf(name);
                for (int i = 0; i < 100; i++) {
                    Object a = monitorList.get(i);
                    // hack 为了在批量重偏向发生后再次加锁,前面使用了轻量级锁的对象
                    if (i == 20) {
                        a = monitorList.get(9);
                    }

                    synchronized (a) {
                        if (i == 10) {
                            //已经经过偏向锁撤销，并使用轻量级锁的对象，释放后  状态依为001 无锁状态
                            System.out.println("t2 i=10 get(1)预期是无锁" + ClassLayout.parseInstance(monitorList.get(1)).toPrintable());
                            //因为和t1交替使用对象a 没有发生竞争，但偏向锁已偏向，另外不满足重偏向条件，所以使用轻量级锁
                            System.out.println("t2 i=10 get(i) 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());
                        }
                        if (i == 19) {
                            //已经经过偏向锁撤销，并使用轻量级锁的对象，在批量重偏向发生后。不会影响现有的状态  状态依然为001
                            System.out.println("t2  i=19  get(10)预期是无锁" + 10 + ClassLayout.parseInstance(monitorList.get(10)).toPrintable());
                            //满足重偏向条件后，已偏向的对象可以重新使用偏向锁 将线程id指向当前线程，101
                            System.out.println("t2  i=19  get(i) 满足重偏向条件20 预期偏向锁 " + i + ClassLayout.parseInstance(a).toPrintable());
                            //满足重偏向条件后，已偏向还为需要加锁的对象依然偏向线程1 因为偏向锁的撤销是发生在下次加锁的时候。这里没有执行到同步此对象，所以依然偏向t1
                            System.out.println("t2  i=19  get(i) 满足重偏向条件20 但后面的对象没有被加锁，所以依旧偏向t1 " + i + ClassLayout.parseInstance(monitorList.get(40)).toPrintable());
                        }
                        if (i == 20) {
                            //满足重偏向条件后，再次加锁之前使用了轻量级锁的对象，依然轻量级锁，证明重偏向这个状态只针对偏向锁。已经发生锁升级的，不会退回到偏向锁
                            System.out.println("t2  i=20 满足偏向条件之后，之前被设置为无锁状态的对象，不可偏向，这里使用的是轻量级锁  get(9)预期是轻量级锁 " + ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
        TimeUnit.SECONDS.sleep(5);
    }


    /**
     * 接下来我们分析两个批量偏向撤销的相关案例
     * （禁止偏向锁延迟的情况下：-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0）
     * <p>
     * 案例一：
     */
    public void test03() throws InterruptedException {
        List<Object> monitorList = new ArrayList<Object>();
        for (int i = 0; i < 100; i++) {
            monitorList.add(new Object());
        }
        Thread t1 = new Thread(() -> {
            System.out.println("加锁前 get(0) 应该是无锁可偏向 " + ClassLayout.parseInstance(monitorList.get(0)).toPrintable());
            for (Object minotor : monitorList) {
                synchronized (minotor) {
                    System.out.print("加锁 >");
                }
            }
            System.out.println();
            System.out.println("加锁后 get(0) 应该是偏向锁" + ClassLayout.parseInstance(monitorList.get(0)).toPrintable());
            try {
                TimeUnit.SECONDS.sleep(1000);//这里不让线程死，防止线程ID复用
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(5);
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                Object minotor = monitorList.get(i);
                synchronized (minotor) {
                    System.out.println(Thread.currentThread().getId() + "加锁 >");
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 9) {//这里刚好是第19个上锁的（同样是第19个偏向锁升级的）
                    System.out.println();
                    System.out.println("加锁后 get(9) 应该是无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                }
                if (i == 10) {//这里刚好是第21个上锁的
                    System.out.println();
                    System.out.println("加锁后 get(10) 应该是偏向锁 偏向t2 " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                }
                if (i == 50) {//50开始升级为轻量级锁（同样是第21个偏向锁升级的）
                    System.out.println();
                    System.out.println("加锁后 get(50) 无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                }
                if (i == 59) {//60（同样是第39个偏向锁升级的）
                    System.out.println();
                    System.out.println("加锁后 get(59) 无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                }
                if (i == 69) {//69（同样是第59个偏向锁升级的）
                    System.out.println();
                    System.out.println("加锁后 get(69) 无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(i)).toPrintable());
                    Object a1 = new Object();
                    synchronized (a1) {
                        System.out.println("偏向撤销发生后的该类新建的对象都不会再偏向任何线程 " + ClassLayout.parseInstance(a1).toPrintable());
                    }
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 99; i >= 0; i--) {
                Object minotor = monitorList.get(i);
                synchronized (minotor) {
                    System.out.println(Thread.currentThread().getId() + "加锁 >");
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /**
                 * 重点：重偏向撤销
                 */
                if (i == 40) {//40升级为轻量级锁（同样是第40个偏向锁升级的，这时候发生偏向撤销）
                    System.out.println();
                    System.out.println("加锁后 get(" + i + ") 应该是无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(0)).toPrintable());
                    Object a1 = new Object();
                    synchronized (a1) {
                        System.out.println("偏向撤销发生后的该类新建的对象都不会再偏向任何线程 " + ClassLayout.parseInstance(a1).toPrintable());
                    }
                }
                if (i == 30) {//39升级为轻量级锁（同样是第42个偏向锁升级的）
                    System.out.println();
                    System.out.println("加锁后 get(" + i + ") 应该是无锁（轻量级锁释放） " + ClassLayout.parseInstance(monitorList.get(0)).toPrintable());
                    Object a1 = new Object();
                    synchronized (a1) {
                        System.out.println("偏向撤销发生后的该类新建的对象都不会再偏向任何线程 " + ClassLayout.parseInstance(a1).toPrintable());
                    }
                }
            }
        });
        t2.start();
        TimeUnit.MILLISECONDS.sleep(50);
        t3.start();
    }

    public void test04() throws InterruptedException {
        List<Object> list = new ArrayList<>();
        List<Object> list2 = new ArrayList<>();
        List<Object> list3 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Object());
            list2.add(new Object());
            list3.add(new Object());
        }
        //偏向锁
        System.out.println("初始状态" + 10 + ClassLayout.parseClass(Object.class).toPrintable());

        Thread t1 = new Thread() {
            String name = "1";

            public void run() {
                System.out.printf(name);
                for (Object a : list) {
                    synchronized (a) {
                        if (a == list.get(10)) {
                            //偏向锁
                            System.out.println("t1 预期是偏向锁" + 10 + ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        Thread.sleep(5000);
        //偏向锁
        System.out.println("main 预期是偏向锁" + 10 + ClassLayout.parseInstance(list.get(10)).toPrintable());
        Thread t2 = new Thread() {
            String name = "2";

            public void run() {
                System.out.printf(name);
                for (int i = 0; i < 100; i++) {
                    Object a = list.get(i);
                    synchronized (a) {
                        if (a == list.get(10)) {
                            System.out.println("t2 i=10 get(1)预期是无锁" + ClassLayout.parseInstance(list.get(1)).toPrintable());//偏向锁
                            System.out.println("t2 i=10 get(10) 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                        if (a == list.get(19)) {
                            System.out.println("t2  i=19  get(10)预期是无锁" + 10 + ClassLayout.parseInstance(list.get(10)).toPrintable());//偏向锁
                            System.out.println("t2  i=19  get(19) 满足重偏向条件20 预期偏向锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                            System.out.println("类的对象累计撤销达到20");
                        }
                    }
                }
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
        Thread.sleep(5000);

        Thread t3 = new Thread() {
            String name = "3";

            public void run() {
                System.out.printf(name);
                for (Object a : list2) {
                    synchronized (a) {
                        if (a == list2.get(10)) {
                            System.out.println("t3 预期是偏向锁" + 10 + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                    }
                }
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t3.start();
        Thread.sleep(5000);

        Thread t4 = new Thread() {
            String name = "4";

            public void run() {
                System.out.printf(name);
                for (int i = 0; i < 100; i++) {
                    Object a = list2.get(i);
                    synchronized (a) {
                        if (a == list2.get(10)) {
                            System.out.println("t4 i=10 get(1)预期是无锁" + ClassLayout.parseInstance(list2.get(1)).toPrintable());//偏向锁
                            System.out.println("t4 i=10 get(10) 当前不满足重偏向条件 20 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                        if (a == list2.get(19)) {
                            System.out.println("t4  i=19  get(10)预期是无锁" + 10 + ClassLayout.parseInstance(list2.get(10)).toPrintable());//偏向锁
                            System.out.println("t4 i=19 get(19) 当前满足重偏向条件 20 但A类的对象累计撤销达到40 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                            System.out.println("类的对象累计撤销达到40");
                        }
                        if (a == list2.get(20)) {
                            System.out.println("t4 i=20 get(20) 当前满足重偏向条件 20 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                    }
                }
            }
        };
        t4.start();
        Thread.sleep(5000);
        System.out.println("main 预期是偏向锁" + 10 + ClassLayout.parseInstance(list3.get(0)).toPrintable());//偏向锁
        Thread t5 = new Thread() {
            String name = "5";

            public void run() {
                System.out.printf(name);
                for (Object a : list3) {
                    synchronized (a) {
                        if (a == list3.get(10)) {
                            System.out.println("t5 预期是轻量级锁，类的对象累计撤销达到40 不可以用偏向锁了" + 10 + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                    }
                }
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();
        Thread.sleep(5000);
        System.out.println("main 预期是偏向锁" + 10 + ClassLayout.parseInstance(list.get(10)).toPrintable());//偏向锁

        Thread t6 = new Thread() {
            String name = "6";

            public void run() {
                System.out.printf(name);
                for (int i = 0; i < 100; i++) {
                    Object a = list3.get(i);
                    synchronized (a) {
                        if (a == list3.get(10)) {
                            System.out.println("t6 i=10 get(1)预期是无锁" + ClassLayout.parseInstance(list3.get(1)).toPrintable());//偏向锁
                            System.out.println("t6 i=10 get(10) 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                        if (a == list3.get(19)) {
                            System.out.println("t6  i=19  get(10)预期是无锁" + 10 + ClassLayout.parseInstance(list3.get(10)).toPrintable());//偏向锁
                            System.out.println("t6  i=19  get(19) 满足重偏向条件20 但类的对象累计撤销达到40 不可以用偏向锁了 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                    }
                }

                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t6.start();
        Thread.sleep(5000);

        System.out.println("由于撤销锁次数达到默认的 BiasedLockingBulkRevokeThreshold=40 这里实例化的对象 是无锁状态" + ClassLayout.parseInstance(new Object()).toPrintable());//偏向锁
        System.out.println("撤销偏向后状态" + 10 + ClassLayout.parseInstance(new Object()).toPrintable());//偏向锁
    }
}




