package com.peijun.threadgroupapi.demo;

public class ThreadGroupCreate {
    public static void main(String[] args) {
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup groupA = new ThreadGroup("GroupA");

        System.out.println("groupA.getParent() == currentGroup ? "
                + (groupA.getParent() == currentGroup));

        ThreadGroup groupB = new ThreadGroup(groupA, "GroupB");
        System.out.println("groupB.getParent() == groupA ? "
                + (groupB.getParent() == groupA));
    }
}
