package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:00
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 主动对象方的程序
 *
 * 该接口定义了主动对象的接口API。
 */
public interface ActiveObject {

    public abstract Result<String> makeString(int count, char fillchar);

    public abstract void displayString(String string);
}
