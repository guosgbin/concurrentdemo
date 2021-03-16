package com.peijun.threadapi;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 启动一个Java程序 会启动几个线程 https://www.cnblogs.com/mymelody/p/5611691.html
 *
 * 6-Monitor Ctrl-Break
 * 5-Attach Listener
 * 4-Signal Dispatcher  //分发处理发送给JVM信号的线程
 * 3-Finalizer //调用对象的finalize方法的线程，就是垃圾回收的线程
 * 2-Reference Handler //清除reference的线程
 * 1-main //主线程
 * JDK1.8
 */
public class StartJavaCodeThreadCount {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "-" + threadInfo.getThreadName());
        }
    }
}
