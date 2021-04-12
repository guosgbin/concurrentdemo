package com.peijun.singleThreadedExcution.use;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/11 23:03
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Gate {
    private int counter = 0;
    private String name = "NoName";
    private String address = "NoWhere";

    /**
     * 改为同步方法
     */
    public synchronized void pass(String name, String address) {
        try {
            this.counter++;
            this.name = name;
            // 睡眠是为了让效果更明显
            TimeUnit.MILLISECONDS.sleep(1);
            this.address = address;
            check();
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * 当最后一个通过门的人的名字第一个字和地址的第一个字不相等时 说明异常
     */
    private void check() {
        String firstName = name.substring(0, 1);
        String firstAddress = address.substring(0, 1);
        if (!Objects.equals(firstName, firstAddress)) {
            System.out.println("----< 数据出错> ----- : " + toString());
        }
    }

    /**
     * 改为同步方法
     */
    public String toString() {
        return "第" + counter + "个:" + name + ", " + address;
    }
}
