package com.peijun.waitnotify;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/26 14:56
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * wait notify 引入例子
 */
public class EventQueue {
    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            if (eventQueue.size() >= max) {
                try {
                    console("队列满了啊...");
                    // 假如队列已满 则提交的线程将被阻塞
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            console("提交了一个新的任务...");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                if (eventQueue.isEmpty()) {
                    try {
                        console("队列为空啊...");
                        // 假如任务队列为空，则阻塞工作线程
                        eventQueue.wait();
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            console("任务" + event + "被处理了...");
            return event;
        }
    }

    private void console(String msg) {
        System.out.printf("%s:%s\n", Thread.currentThread().getName(), msg);
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();

        new Thread(() -> {
            for (;;) {
                eventQueue.offer(new Event());
            }
        }, "Producer").start();

        new Thread(() -> {
            for (;;) {
                eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ignored) {
                }
            }
        }, "Consumer").start();
    }
}
