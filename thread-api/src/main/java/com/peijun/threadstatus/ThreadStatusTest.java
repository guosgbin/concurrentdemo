package com.peijun.threadstatus;

import org.junit.Test;

import java.io.IOException;
import java.sql.Time;
import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试 线程的状态
 * <li>1.【NEW】 --> 【RUNNABLE】 start方法 {@link #testNewToRunnable()}</li>
 * =========================================================
 * <li>2.【RUNNABLE】 --> 【TERMINATED】 线程正常运行结束  {@link #testRunnableToTerminate01()}</li>
 * <li>3.【RUNNABLE】 --> 【TERMINATED】 线程意外运行结束 {@link #testRunnableToTerminate02()}</li>
 * =========================================================
 * <li>4.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】sleep(long)方法 {@link #testRunnableToTimeWaiting01()}</li>
 * <br>
 * <li>5.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】wait(long)方法 {@link #testRunnableToTimeWaiting02()}</li>
 * <li>6.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【BLOCKED】wait(long)方法 {@link #testRunnableToTimeWaiting03()}</li>
 * <br>
 * <li>7.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】join(long)方法 {@link #testRunnableToTimeWaiting04()}</li>
 * <li>8.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【BLOCKED】join(long)方法 {@link #testRunnableToTimeWaiting05()}</li>
 * <br>
 * <li>9.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】parkNanos(long)方法 {@link #testRunnableToTimeWaiting06()}</li>
 * <li>10.【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】parkUntil(long)方法 {@link #testRunnableToTimeWaiting07()}</li>
 * =========================================================
 * <li>11.【RUNNABLE】 --> 【WAITING】
 *   <br>&emsp;             --> 【RUNNABLE】wait()方法 {@link #testRunnableToWaiting01()}
 *   <br>&emsp;             --> 【BLOCKED】wait()方法 {@link #testRunnableToWaiting01()}</li>
 * <br>
 * <li>12.【RUNNABLE】 --> 【WAITING】
 *   <br>&emsp;             --> 【RUNNABLE】join()方法 {@link #testRunnableToWaiting02()}
 *   <br>&emsp;             --> 【BLOCKED】join()方法 </li>
 * <li>13.【RUNNABLE】 --> 【WAITING】--> 【RUNNABLE】  park()方法 {@link #testRunnableToWaiting03()}</li>
 *
 * @author: Dylan kwok GSGB
 * @date: 2021/4/6 21:55
 */
public class ThreadStatusTest {

    // ========================================
    //【NEW】 --> 【RUNNABLE】
    // 调用start方法时
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

    // ---------------------< 分割线 >---------------------

    // ========================================
    //【RUNNABLE】 --> 【TERMINATED】
    // 线程正常结束和意外结束
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

    // ---------------------< 分割线 >---------------------

    // ========================================
    //【RUNNABLE】 --> 【TIMED_WAITING】
    // - sleep(long)
    // - wait(long)
    // - join(long)
    // - LockSupport.parkNanos(long)
    // - LockSupport.parkUntil(long)
    // ========================================

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

    /**
     * parkNanos 单位 纳秒
     * 测试 {@link java.util.concurrent.locks.LockSupport#parkNanos(long)}
     * 该方法会让线程进入【TIMED_WAITING】状态
     * 【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】
     */
    @Test
    public void testRunnableToTimeWaiting06() throws Exception {
        Thread t1 = new Thread(() -> {
            LockSupport.parkNanos(100);
            System.out.println("爷睡醒了...");
            System.out.println("等待完毕后，t2线程的状态："+Thread.currentThread().getState());
            while (true) {}
        }, "测试线程");

        t1.start();
        System.out.println("调用start方法后 -> t1线程的状态：" + t1.getState());
        TimeUnit.NANOSECONDS.sleep(100);
        System.out.println("100纳秒后，t1线程的状态：" + t1.getState());
        System.in.read();
    }

    /**
     * parkUntil 单位 毫秒  等待从纪元时间到指定时间时间戳
     * 也就是说 假如要等待到  2021年4月8日22:22:59  那就需要传入此时间的1970年的时间戳
     *
     * 测试 {@link java.util.concurrent.locks.LockSupport#parkUntil(long)}
     * 该方法会让线程进入【TIMED_WAITING】状态
     * 【RUNNABLE】 --> 【TIMED_WAITING】 --> 【RUNNABLE】
     */
    @Test
    public void testRunnableToTimeWaiting07() throws Exception {
        Thread t1 = new Thread(() -> {
            // 获取当前时间后的5秒钟
            Instant instant = Instant.now().plusSeconds(5);
            long timestamp = instant.toEpochMilli();
            System.out.println("等待截止时间: " + LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
            // 睡眠时间的1秒钟
            System.out.println("睡眠前的时间："+ LocalDateTime.now());
            LockSupport.parkUntil(timestamp);
            System.out.println("睡醒大概时间："+LocalDateTime.now());
            System.out.println("爷睡醒了...");
            System.out.println("等待完毕后，t2线程的状态：" + Thread.currentThread().getState());
            while (true) {
            }
        }, "测试线程");

        t1.start();
        System.out.println("调用start方法后 -> t1线程的状态：" + t1.getState());
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("100纳秒后，t1线程的状态：" + t1.getState());
        System.in.read();
    }

    // ---------------------< 分割线 >---------------------

    // ========================================
    //【RUNNABLE】 --> 【WAITING】
    // - wait()
    // - join()
    // - LockSupport.park()
    // ========================================

    /**
     * {@link Object#wait()}方法会让线程进入 无限等待 状态
     * 当其他线程调用{@link Object#notify()}或者{@link Object#notifyAll()}会唤醒 无限等待的线程
     * 【RUNNABLE】 --> 【WAITING】
     * <li>假如拿到锁了 进入【RUNNABLE】</li>
     * <li>假如未拿到锁 进入【BLOCK】</li>
     */
    @Test
    public void testRunnableToWaiting01() throws Exception {
        final Object monitor = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (monitor) {
                try {
                    System.out.println("t1线程的状态:" + Thread.currentThread().getState());
                    monitor.wait();
                    System.out.println("t1爷被唤醒了，继续执行...");
                    while (true) {
                    }
                } catch (InterruptedException ignored) {
                }
            }
        }, "测试线程");

        Thread t2 = new Thread(() -> {
            synchronized (monitor) {
                try {
                    monitor.notifyAll(); // 唤醒t1线程
                    // 虽然唤醒了t1线程，但是此1秒钟还是占有锁，所以此时t1线程是阻塞状态
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
            }
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1); //
        System.out.println("线程t1的状态为: " + t1.getState());
        t2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("t2唤醒t1线程，但此时t2线程还占有锁，t1线程状态是：" + t1.getState());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("t2唤醒t1线程，且t2线程已经释放锁了，t1线程状态是：" + t1.getState());
        System.in.read();
    }

    /**
     * {@link Thread#join()}方法会让线程进入 无限等待 状态
     * 当等待线程执行完毕后  调用线程根据拿锁情况  决定是否向下执行
     * <li>假如拿到锁了 进入【RUNNABLE】</li>
     * <li>假如未拿到锁 进入【BLOCK】</li>
     * 此例子展示无争抢锁的情况， 争抢锁可以看上面的join有参方法的例子
     *
     * RUNNABLE】 --> 【WAITING】
     *    <li>假如拿到锁了 进入【RUNNABLE】</li>
     *    <li>假如未拿到锁 进入【BLOCK】</li>
     */
    @Test
    public void testRunnableToWaiting02() throws Exception {
        // 线程t1睡眠两秒
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignored) {
            }
        }, "睡眠线程");

        // 线程t2等待线程1执行完毕
        Thread t2 = new Thread(() -> {
            try {
                System.out.println("线程t2开始执行，t2的状态为：" + Thread.currentThread().getState());
                t1.join();
                System.out.println("线程t1执行完毕，t2的状态为：" + Thread.currentThread().getState());
            } catch (InterruptedException ignored) {
            }
        });

        t1.start();
        TimeUnit.MICROSECONDS.sleep(10);
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程t1睡眠中，t2的状态为：" + t2.getState());
        System.in.read();
    }

    /**
     * {@link LockSupport#park()} 方法 假如线程没有许可证 会让当前线程挂起
     * 在其他线程调用LockSupport.unpark(Thread thread)方法并且将当前线程作为参数时，调用LockSupport.park()方法而被阻塞的线程会返回。
     * 如果其他线程调用了阻塞线程的interrupt()方法(中断线程)，设置了中断标志或者线程被虚假唤醒，则阻塞线程也会返回。
     * 所以在调用LockSupport.park()方法时最好使用循环条件判断。
     *
     * 没有许可证情况：    【RUNNABLE】 --> 【WAITING】 --> 【RUNNABLE】
     *
     */
    @Test
    public void testRunnableToWaiting03() throws Exception {
        // 线程t1睡眠两秒
        Thread t1 = new Thread(() -> {
            System.out.println("线程t1开始运行，状态为：" + Thread.currentThread().getState());
            LockSupport.park();
            System.out.println("线程t1被唤醒，状态为：" + Thread.currentThread().getState());
        }, "睡眠线程");

        // 线程t2等待线程1执行完毕
        Thread t2 = new Thread(() -> {
           LockSupport.unpark(t1);
        });

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t2.start();
        System.out.println("线程t1被唤醒，t1的状态为：" + t1.getState());
        System.in.read();
    }
}
