package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:21
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 表示抽象化的请求
 * 具体的请求是MakeStringRequest和DisplayStringRequest
 *
 * 考虑到子类会使用servant字段和future字段，将其定义为protected
 */
abstract class MethodRequest<T> {
    /**
     * 保存负责实际处理的Servant的实例
     */
    protected final Servant servant;

    /**
     * 保存用于设置返回值的FutureRequest的实例。
     * 如果请求没有返回值，那么该字段将不会被使用，会被赋值为null
     */
    protected final FutureResult<T> future;

    protected MethodRequest(Servant servant, FutureResult<T> future) {
        this.servant = servant;
        this.future = future;
    }

    /**
     * 编写执行请求的处理的具体内容
     */
    public abstract void excute();
}
