package com.peijun.guardedsuspension.multiple;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 18:46
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 多个用户点外卖，每个用户的外卖由一个骑手送

        // 点外卖的用户，等待外卖送达
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                // 创建一个队列，用户去这个队列拿数据
                MessageQueue messageQueue = DecoupleTask.createQueue();
                System.out.println(Thread.currentThread().getName() + "开始运行...");
                Message message = messageQueue.getMessage(3000);
                if (message == null) {
                    System.out.println(Thread.currentThread().getName() + " = 骑手也太磨叽了，我等两年了还没送到，我不等了，自己出去吃了 -->" + " 迟到了啊..");
                } else {
                    System.out.println(Thread.currentThread().getName() + " = 哇，外卖到了，开吃开吃 --> " + message.getName());
                }
            }, "等外卖的用户-" + i).start();
            TimeUnit.MILLISECONDS.sleep(10);
        }

        // 从DecoupleTask类中获取上面的生产结果
        Set<Integer> queueIdSet = DecoupleTask.getAllQueueId();
        for (Integer queueId : queueIdSet) {
            // 外卖骑手线程 送一份外卖
            new Thread(() -> {
                try {
                    // 根据queueId获取对应的队列
                    MessageQueue messageQueue = DecoupleTask.getQueueById(queueId);
                    System.out.println(Thread.currentThread().getName() + "开始运行...");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5)); // 模拟耗时
                    Message message = new Message("我是用户"+ queueId +"的外卖");
                    System.out.println(Thread.currentThread().getName() + " = 骑手马上到你家门口 --> " + message.getName());
                    messageQueue.putMessage(message);
                } catch (InterruptedException ignored) {
                }
            }, "外卖骑手-" + queueId).start();
        }

        System.in.read();

    }
}
