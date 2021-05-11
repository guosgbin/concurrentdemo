package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:32
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * Result类相当于Future模式的VirtualData角色。
 * Result类有两个子类：扮演Future角色的FutureResult类和扮演RealData角色的RealResult类。
 */
public abstract class Result<T> {
    public abstract T getResultValue();
}
