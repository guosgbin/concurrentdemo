package com.peijun.future;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/28 21:53
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * realData字段 用于保存稍后创建完毕的RealData的实例字段，我们可以通过setRealData方法设置该字段
 * ready字段，表示是否已经为realData字段赋值，如果为true则表示已经赋值完成
 */
public class FutureData implements Data{
    /**
     *
     */
    private RealData realData;

    /**
     * 表示是否已经产生结果
     */
    private volatile boolean ready = false;

    public synchronized void setRealData(RealData realData) {
        if (ready) {
            // 已经赋值过结果了，直接退出了 balking
            return;
        }
        // 没有赋值过，进行赋值操作
        this.realData = realData;
        ready = true;
        this.notifyAll();

    }

    @Override
    public synchronized String get() {
        while (!ready) {
            // realData未产生则去等待
            try {
                System.out.println(Thread.currentThread().getName() + " 等待中");
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        // 任务已执行完毕，直接返回
        return realData.get();
    }
}
