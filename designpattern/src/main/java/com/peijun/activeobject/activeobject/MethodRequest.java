package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:21
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
abstract class MethodRequest<T> {
    protected final Servant servant;
    protected final FutureResult<T> future;

    protected MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }

    public abstract void excute();
}
