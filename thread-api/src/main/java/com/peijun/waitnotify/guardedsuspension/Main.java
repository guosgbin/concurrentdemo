package com.peijun.waitnotify.guardedsuspension;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:46
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "康康", 3143592L).start();
        new ServerThread(requestQueue, "李明", 6535897L).start();
    }
}
