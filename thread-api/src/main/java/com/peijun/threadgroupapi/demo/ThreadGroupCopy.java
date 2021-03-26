package com.peijun.threadgroupapi.demo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ThreadGroupCopy {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup parentGroup = new ThreadGroup("ParentGroup");
        init(parentGroup); // 初始化线程组和线程关系
        ThreadGroup[] threadGroups = new ThreadGroup[parentGroup.activeGroupCount()];
        int enumerate = parentGroup.enumerate(threadGroups, true);
        System.out.println(Arrays.toString(threadGroups));
    }

    private static void init(ThreadGroup parentGroup) throws InterruptedException {
        ThreadGroup sonGroupA = new ThreadGroup(parentGroup, "sonGroupA");
        ThreadGroup sonGroupB = new ThreadGroup(parentGroup, "sonGroupB");
        ThreadGroup sonGroupC = new ThreadGroup(parentGroup, "sonGroupC");
        ThreadGroup sonGroupD = new ThreadGroup(sonGroupA, "sonGroupD");
        TimeUnit.SECONDS.sleep(1);
    }
}
