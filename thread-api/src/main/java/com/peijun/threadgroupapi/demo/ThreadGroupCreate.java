package com.peijun.threadgroupapi.demo;

public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程的线程组：" + currentGroup.getName());

        // 默认创建的线程组的默认父级线程组是 调用创建方法的线程
        ThreadGroup groupA = new ThreadGroup("GroupA");
        System.out.println("新创建的线程组的父线程组是否是currentGroup：" + (groupA.getParent() == currentGroup));

        // 显式指定新建的线程组的父级线程组
        ThreadGroup groupB = new ThreadGroup(groupA, "GroupB");
        System.out.println("指定新创建的线程组的父级线程组是groupA：" + (groupB.getParent() == groupA));
    }
}
