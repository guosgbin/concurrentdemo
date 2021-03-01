package com.peijun.synckey.principle;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/1 8:57
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试锁粗化
 */
public class LockThick {
    private static Object monitor = new Object();

    public static void main(String[] args) {
        method();
    }

    /**
     * 下面的代码可能被粗化为
     *
     * synchronized (monitor) {
     *   for (int i = 0; i < 10; i++) {
     *       System.out.println(666);
     *   }
     * }
     */
    public static void method() {
        for (int i = 0; i < 10; i++) {
            synchronized (monitor) {
                System.out.println(666);
            }
        }
    }
}
