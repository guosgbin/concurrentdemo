package com.peijun.threadapi.currentthread;

public class MyThread extends Thread {

    public MyThread() {
        System.out.println("MyThread构造方法开始...");
        System.out.println("Thread.currentThread().getName() --> " + Thread.currentThread().getName());
        System.out.println("this.getName() ---> " + this.getName());
        System.out.println("MyThread构造方法结束...");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run方法开始...");
        System.out.println("Thread.currentThread().getName() --> " + Thread.currentThread().getName());
        System.out.println("this.getName() ---> " + this.getName());
        System.out.println("run方法结束...");
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("我是新的线程名");
        System.out.println("> ====================== <");
        t1.start();
    }
}
