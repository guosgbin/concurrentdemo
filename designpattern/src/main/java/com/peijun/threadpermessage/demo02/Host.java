package com.peijun.threadpermessage.demo02;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/25 23:28
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Host {
    private final Helper helper = new Helper();

    public void request(int count, char c) {
        System.out.println("    开始：request( " + count + ", " + c + " )" );
        new Thread(() -> {
            helper.handle(count, c);
        }).start();
        System.out.println("    结束：request( " + count + ", " + c + " )" );
    }
}
