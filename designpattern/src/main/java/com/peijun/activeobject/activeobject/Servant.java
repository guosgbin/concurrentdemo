package com.peijun.activeobject.activeobject;

import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:38
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * Servant类是在ActiveObject一方中负责实际处理的部分。
 * 它有makeString和displayString两个方法，并在妻子执行实际处理
 *
 * 然后Servant类实现了ActiveObject接口，可以说这是该模式最优雅的一部分了
 * 也就是说，Proxy类和Servant类都实现了ActiveObject这个共同的接口
 * 这样一来，就可以从代码上非常明确的看出以下两者是一致的。
 * 1.主动对象可以调用的方法群（由Proxy类实现的方法群）
 * 2.主动对象可以实际执行的方法群（由Servant类实现的方法群）
 */
class Servant implements ActiveObject {
    @Override
    public Result<String> makeString(int count, char fillchar) {
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = fillchar;
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        return new RealResult<String>(new String(buffer));
    }


    @Override
    public void displayString(String string) {
        try {
            System.out.println("displayString:" + string);
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException ignored) {
        }
    }
}
