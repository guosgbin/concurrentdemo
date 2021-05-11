package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 扮演Future模式的Future角色，用于操作返回值。
 * 我们可以使用setResult方法设置返回值，使用getResultValue方法获取返回值
 *
 * 使用了Guarded Suspension 保护性暂停模式 守护条件是 “返回值已经被设置了”
 */
public class FutureResult<T> extends Result<T> {

    private Result<T> result;
    private boolean ready = false;

    public synchronized void setResult(Result<T> result) {
        this.result = result;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized T getResultValue() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        return result.getResultValue();
    }
}
