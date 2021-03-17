package com.peijun.threadapi.priority;

/**
 * 测试线程组的优先级最大值
 */
public class PriorityDemo02 {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("test-group");
        // 将创建出来的线程组的优先级设置为8
        group.setMaxPriority(8);
        // 创建一个线程 设置新的线程优先级为10
        Thread thread = new Thread(group, "测试线程");
        thread.setPriority(10);
        System.out.println("测试线程的优先级 ---> " + thread.getPriority());
    }
}
