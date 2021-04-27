package com.peijun.threadpermessage.demo01;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/25 23:15
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("main方法---我是XX运营，我来提需求了，开始...");
        ProductManager pm = new ProductManager();
        // 产品经理去叫程序员去写需求
        pm.call("我是需求1，星期一评审，周六上线???");
        pm.call("我是需求2，星期二评审，周六上线???");
        pm.call("我是需求3，星期三评审，周六上线???");
        pm.call("我是需求4，星期四评审，周六上线???");
        pm.call("我是需求5，星期五评审，周六上线???");
        System.out.println("main方法---产品经理告诉运营，程序员正在开发了，结束...");
    }
}
