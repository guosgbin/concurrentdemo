package com.peijun.singlethreadedexcution.notuse;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/11 23:15
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class People extends Thread {
    // 表示要通过的门
    private final Gate gate;
    // 姓名
    private final String myname;
    // 出生地
    private final String myaddress;

    public People(Gate gate, String myname, String myaddress) {
        this.gate = gate;
        this.myname = myname;
        this.myaddress = myaddress;
    }

    public void run() {
        System.out.println(myname + " 开始过门...");
        for (int i = 0; i < 100; i++) {
            gate.pass(myname, myaddress);
        }
    }
}
