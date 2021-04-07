package com.peijun.threadstatus;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/6 21:55
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试 线程的状态
 *
 * 1.【NEW】 --> 【RUNNABLE】 start方法 {@link #testNewToRunnable()}
 *
 * 2.【RUNNABLE】 --> 【TERMINATED】 线程正常运行结束  {@link #testRunnableToTerminate01()}
 * 3.【RUNNABLE】 --> 【TERMINATED】 线程意外运行结束 {@link #testRunnableToTerminate02()}
 *
 * 4.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】sleep(long)方法 {@link #testRunnableToTimeWaiting01()}
 *
 * 5.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】wait(long)方法 {@link #testRunnableToTimeWaiting02()}
 * 6.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【BLOCKED】wait(long)方法 {@link #testRunnableToTimeWaiting03()}
 *
 * 7.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】join(long)方法 {@link #testRunnableToTimeWaiting04()}
 * 8.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【BLOCKED】join(long)方法 {@link #testRunnableToTimeWaiting05()}
 */
public class ThreadStatusTest {

    // ========================================
    // 调用start方法时
    //【NEW】 --> 【RUNNABLE】
    // ========================================

    /**
     * 只有调用了start方法才会到
     *【NEW】 --> 【RUNNABLE】
     */
    @Test
    public void testNewToRunnable() {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }, "测试线程");
        System.out.println("未调用start时 -> 当前线程的状态：" + thread.getState());
        thread.start();
        System.out.println("调用start方法后 -> 当前线程的状态：" + thread.getState());
    }

    // ========================================
    // 调用start方法时
    //【RUNNABLE】 --> 【TERMINATED】
    // ========================================

    /**
     * 线程正常结束
     *【RUNNABLE】 --> 【TERMINATED】
     */
    @Test
    public void testRunnableToTerminate01() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }, "测试线程");
        System.out.println("未调用start时 -> 当前线程的状态：" + thread.getState());
        thread.start();
        System.out.println("调用start方法后 -> 当前线程的状态：" + thread.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("线程正常执行完后 -> 当前线程的状态：" + thread.getState());
    }

    /**
     * 线程意外退出
     *【RUNNABLE】 --> 【TERMINATED】
     */
    @Test
    public void testRunnableToTerminate02() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                int i = 1 / 0;
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception ex) {
                System.out.println("异常原因: " + ex.getMessage());
            }
        }, "测试线程");
        System.out.println("未调用start时 -> 当前线程的状态：" + thread.getState());
        thread.start();
        System.out.println("调用start方法后 -> 当前线程的状态：" + thread.getState());
        TimeUnit.MICROSECONDS.sleep(100);
        System.out.println("线程意外退出 -> 当前线程的状态：" + thread.getState());
    }

    /**
     * 调用sleep方法 使线程的状态从 RUNNABLE 到 TIMED_WAITING  恢复运行后  又回到了【RUNNABLE】状态
     *【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】
     */
    @Test
    public void testRunnableToTimeWaiting01() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                while (true){

                }
            } catch (Exception ignored) {
            }
        }, "测试线程");
        thread.start();
        System.out.println("调用start方法后 -> 当前线程的状态：" + thread.getState());
        TimeUnit.MICROSECONDS.sleep(100);
        System.out.println("线程sleep中 -> 当前线程的状态：" + thread.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程运行中 -> 当前线程的状态：" + thread.getState());
        thread.join();
    }

    /**
     * 调用wait(timeout)方法
     *【RUNNABLE】 --> 【TIMED_WAITING】
     * 注意，因为wait方法会释放锁，所以当调用wait(long)方法后，会根据当前线程是否再次争抢到锁来决定当前线程的状态。
     * - 抢到锁：-->【RUNNABLE】
     * - 未抢到锁：-->【BLOCKED】
     *
     * 本方法测试 无争抢锁的情况
     */
    @Test
    public void testRunnableToTimeWaiting02() throws InterruptedException {
        Thread thread = new Thread(() -> {
           synchronized (this) {
               try {
                   this.wait(1000);
                   System.out.println("=====");
                   while (true){

                   }
               } catch (InterruptedException ignored) {
               }
           }
        }, "测试线程");
        thread.start();
        System.out.println("调用start方法后 -> 当前线程的状态：" + thread.getState());
        TimeUnit.MICROSECONDS.sleep(100);
        System.out.println("线程sleep中 -> 当前线程的状态：" + thread.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程运行中 -> 当前线程的状态：" + thread.getState());
        thread.join();
    }

    /**
     * 调用wait(timeout)方法
     *【RUNNABLE】 --> 【TIMED_WAITING】
     * 注意，因为wait方法会释放锁，所以当调用wait(long)方法后，会根据当前线程是否再次争抢到锁来决定当前线程的状态。
     * - 抢到锁：-->【RUNNABLE】
     * - 未抢到锁：-->【BLOCKED】
     *
     * 本方法测试 有争抢锁情况
     */
    @Test
    public void testRunnableToTimeWaiting03() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (this) {
                try {
                    // 睡眠1秒
                    this.wait(1000);
                    System.out.println("=====");
                    while (true){

                    }
                } catch (InterruptedException ignored) {
                }
            }
        }, "测试线程");

        // 占用锁不放的线程
        Thread t2 = new Thread(() -> {
            try {
                // 睡眠0.5秒，这时把上面的t1线程锁占住了，t1线程会阻塞
                TimeUnit.MILLISECONDS.sleep(500);
                synchronized (this) {
                    System.out.println("=====");
                    while (true) {
                        // 不放锁
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        System.out.println("调用start方法后 -> t1线程的状态：" + t1.getState());
        TimeUnit.MICROSECONDS.sleep(100);
        System.out.println("线程sleep中 -> t1线程的状态：" + t1.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("线程运行中 -> t1线程的状态：" + t1.getState());
        System.out.println("线程运行中 -> t2线程的状态：" + t2.getState());
        t1.join();
        t2.join();
    }

    /**
     * 调用join(timeout)方法
     *【RUNNABLE】 --> 【TIMED_WAITING】
     * 注意，因为wait方法会释放锁，所以当调用join(timeout)方法后，会根据当前线程是否再次争抢到锁来决定当前线程的状态。
     * - 抢到锁：-->【RUNNABLE】
     * - 未抢到锁：-->【BLOCKED】
     *
     * 本方法测试 无争抢锁情况
     */
    @Test
    public void testRunnableToTimeWaiting04() throws InterruptedException, IOException {
        Thread t1 = new Thread(() -> {
                try {
                    System.out.println("t1线程开始运行...");
                    for (int i = 0; i < 10000000; i++);
                    // 线程t1运行1秒
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
        }, "线程t1");

        // t2线程等待t1线程执行完毕再去执行
        Thread t2 = new Thread(() -> {
                try {
                    System.out.println("t2线程开始等待t1线程执行完毕...");
                    t1.join(500);
                    while (true) {

                    }
                } catch (InterruptedException ignored) {
                }
        }, "线程t2");

        t1.start();
        t2.start();
        System.out.println("调用start方法后 -> t2线程的状态：" + t2.getState());
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println("调用t1.join(long)方法后 -> t2线程的状态：" + t2.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("等待超时后 -> t2线程的状态：" + t2.getState());
        System.in.read();
    }


    /**
     * 调用join(timeout)方法
     *【RUNNABLE】 --> 【TIMED_WAITING】
     * 注意，因为wait方法会释放锁，所以当调用join(timeout)方法后，会根据当前线程是否再次争抢到锁来决定当前线程的状态。
     * - 抢到锁：-->【RUNNABLE】
     * - 未抢到锁：-->【BLOCKED】
     *
     * 本方法测试 未抢到锁情况
     */
    @Test
    public void testRunnableToTimeWaiting05() throws InterruptedException, IOException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("t1线程开始运行...");
                for (int i = 0; i < 10000000; i++);
                // 线程t1运行1秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }, "线程t1");

        // t2线程等待t1线程执行完毕再去执行
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2线程开始等待t1线程执行完毕...");
                t1.join(500);
                System.out.println("这句话打不出了");
                while (true) {

                }
            } catch (InterruptedException ignored) {
            }
        }, "线程t2");

        // t2线程等待t1线程执行完毕再去执行
        Thread t3 = new Thread(() -> {
            synchronized (t1) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "线程t3");

        t1.start();
        t2.start();
        System.out.println("调用start方法后 -> t2线程的状态：" + t2.getState());
        TimeUnit.MILLISECONDS.sleep(100); // 让t2先运行
        t3.start(); // 此线程是占用锁对象t1的
        TimeUnit.MILLISECONDS.sleep(300);
        System.out.println("调用t1.join(long)方法后 -> t2线程的状态：" + t2.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println("因为join方法的加锁对象是调用者，也就是t1，而此时t1对象作为锁被t3线程拿着不放 -> t2线程的状态：" + t2.getState());
        TimeUnit.SECONDS.sleep(6);
        System.out.println("因 -> t2线程的状态：" + t2.getState());
        System.in.read();
    }
}
