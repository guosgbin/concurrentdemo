package com.peijun.threadapi.join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/17 22:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class QueryResultTask extends Thread {
    private final String origin;
    private final String destination;
    private final List<String> fightList = new ArrayList<>();

    public QueryResultTask(String airline, String origin, String destination) {
        super("[" + airline + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(), origin, destination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            fightList.add(getName() + "-" + randomVal);
            System.out.printf("The Flight:%s list query successful\n", getName());
        } catch (InterruptedException ignored) {
        }
    }

    public List<String> get() {
        return this.fightList;
    }
}
