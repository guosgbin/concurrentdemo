package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:02
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * ActiveObjectFactory类是用于构成ActiveObject对象的类。
 * 在该类中有一个名为createActiveObject的静态方法，该方法会创建ActiveObject对象。
 *
 * 想要创建ActiveObject对象，必须将一下四个类的实例组合起来。
 * 1.Servant：执行实际处理的类（实现了ActiveObject接口的类）。
 * 2.ActivationQueue：按顺序保存MethodRequest对象的类。
 * 3.SchedulerThread：调用execute方法处理MethodRequest对象的类。
 * 4.Proxy：将方法调用转换为MethodRequest对象的类（实现了ActiveObject接口的类）。
 */
public class ActiveObjectFactory {
    public static ActiveObject createActiveObject() {
        // 创建一个Servant对象，该对象是真正处理请求的类，拥有 主动对象 实际执行的方法群
        Servant servant = new Servant();
        // 创建一个存放请求的队列
        ActivationQueue queue = new ActivationQueue();
        // 创建一个循环执行MethodRequest的excute方法的线程，当队列中没有任务时，wait等待
        SchedulerThread scheduler = new SchedulerThread(queue);
        // 创建一个Proxy对象，该对象是 主动对象 可以调用的方法群
        Proxy proxy = new Proxy(scheduler, servant);
        // 开启线程
        scheduler.start();
        // 返回代表 主动对象 可以调用的方法群
        return proxy;
    }
}
