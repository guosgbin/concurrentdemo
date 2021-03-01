package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

public class L {
    private final String str = "hello world";

    public static void main(String[] args) {
        L l = new L();  //new 一个对象
        System.out.println(ClassLayout.parseInstance(l).toPrintable());//输出 l对象 的布局
    }
}