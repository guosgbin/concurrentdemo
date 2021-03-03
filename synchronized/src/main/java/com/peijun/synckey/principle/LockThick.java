package com.peijun.synckey.principle;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/1 8:57
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试锁粗化
 * <p>
 */
public class LockThick {
    private static Object monitor = new Object();

    public static void main(String[] args) {
        method();
        method02();
    }

    /**
     * 下面的代码可能被粗化为
     * <p>
     * synchronized (monitor) {
     * for (int i = 0; i < 10; i++) {
     * System.out.println(666);
     * }
     * }
     */
    public static void method() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            synchronized (monitor) {

            }
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("总耗时：%s毫秒%n", endTime - startTime);
    }

    public static void method02() {
        long startTime = System.currentTimeMillis();
        synchronized (monitor) {
            for (int i = 0; i < 100000000; i++) {

            }
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("总耗时：%s毫秒", endTime - startTime);
    }
}
