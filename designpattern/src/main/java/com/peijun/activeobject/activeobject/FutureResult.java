package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:33
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
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
    public T getResultValue() {
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        return result.getResultValue();
    }
}
