package com.peijun.volatilekey.application;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/26 14:55
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * volatile在单例模式应用
 */
public class Singleton {
    private static volatile Singleton INSTANCE;
    private static int a, b, c;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
//                    a = 1;
//                    b = 2;
                    INSTANCE = new Singleton();
//                    c = a + b;
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
    }
}
