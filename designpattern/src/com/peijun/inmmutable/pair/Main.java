package com.peijun.inmmutable.pair;

import java.util.Objects;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/14 8:23
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        MutablePerson mutable = new MutablePerson("start", "start");
        new CrackerThread(mutable).start();
        new CrackerThread(mutable).start();
        new CrackerThread(mutable).start();
        for (int i = 0; true; i++) {
            mutable.setPerson("" + i, "" + i);
        }
    }

    static class CrackerThread extends Thread {
        private final MutablePerson mutable;

        public CrackerThread(MutablePerson mutable) {
            this.mutable = mutable;
        }

        @Override
        public void run() {

            while (true) {
                ImmutablePerson immutable = new ImmutablePerson(mutable);
                if (!Objects.equals(immutable.getName(), immutable.getAddress())) {
                    System.out.println(Thread.currentThread().getName() + "----- 出现异常 ----- " + immutable);
                }
            }
        }
    }
}
