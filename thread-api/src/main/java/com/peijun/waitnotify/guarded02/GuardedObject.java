package com.peijun.waitnotify.guarded02;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 19:21
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 *
 * 被保护的对象
 */
public class GuardedObject {
    private int guardedId;
    // 结果
    private Object result;

    public int getGuardedId() {
        return guardedId;
    }

    public void setGuardedId(int guardedId) {
        this.guardedId = guardedId;
    }

    /**
     * 守护方法
     */
    public synchronized Object getResult() {
        // 守护条件不满足则等待
        while (result == null) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        // 守护条件满足则去执行
        return result;
    }

    /**
     * 守护方法 带超时功能
     */
    public synchronized Object getResult(long millis) {
        // 守护条件不满足则等待
        long startTime = System.currentTimeMillis(); // 获取调用方法的时间
        long wasteTime = 0; // 记录已经消耗的时间
        while (result == null) {
            try {
                long delay = millis - wasteTime; // 计算需要睡眠的时间
                if (delay <= 0) {
                    break;
                }
                this.wait(delay); // 等待剩余时间
                // 假如被虚假唤醒，重新计算已经消耗的时间
                wasteTime = System.currentTimeMillis() - startTime;
            } catch (InterruptedException ignored) {
            }
        }
        // 守护条件满足则去执行
        return result;
    }

    /**
     * 改变result的方法
     */
    public synchronized void putResult(Object result) {
            // 条件满足，通知等待线程
            this.result = result;
            this.notifyAll();
    }
}
