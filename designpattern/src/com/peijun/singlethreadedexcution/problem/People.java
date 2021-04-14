package com.peijun.singlethreadedexcution.problem;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/12 22:02
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 代表人的线程类
 */
public class People extends Thread {
    // 左手的筷子
    private final Chopsticks leftHand;
    // 右手的筷子
    private final Chopsticks rightHand;

    public People(String name, Chopsticks leftHand, Chopsticks rightHand) {
        super(name);
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public void run() {
        while (true) {
           synchronized (leftHand) {
               System.out.println(getName() + "的左手拿到了筷子: " + leftHand);
               synchronized (rightHand) {
                   System.out.println(getName() + "的右手拿到了筷子: " + rightHand);
                   System.out.println(getName() + "两个筷子都拿到了, 开吃...");
               }
           }
       }
    }
}
