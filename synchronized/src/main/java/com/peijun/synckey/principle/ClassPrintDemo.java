package com.peijun.synckey.principle;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/28 22:07
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 打印内存规则
 */
public class ClassPrintDemo {

    public static void main(String[] args) {
        ClassPrintDemo demo = new ClassPrintDemo();
        String layoutStr = ClassLayout.parseInstance(demo).toPrintable();
        System.out.println(layoutStr);
    }
}
