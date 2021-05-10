package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:43
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
class SchedulerThread extends Thread{
    private final ActivationQueue queue;

    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }

    @Override
    public void run() {
        while (true) {
            MethodRequest request = queue.takeRequest();
            request.excute();
        }
    }
}
