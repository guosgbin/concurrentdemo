package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:43
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 向queue中增加request的部分相当于对“主动对象”方法的“调用”。
 * 另外，执行request的execute方法的部分相当于“主动对象”方法的“执行”
 *
 * 下面两个线程是不同的线程
 * 1.调用invoke方法的线程
 * 2.调用execute方法的线程
 *
 * 1 是调用Proxy类的makeString方法和displayString方法的线程。
 * 具体而言是与MakerClientThread和DisplayClientThread对应的线程
 *
 * 2 是与SchedulerThread类对应的线程
 *
 * 执行invoke方法的线程是Producer角色，它会通过putRequest方法将request添加到queue中。
 * 执行execute方法的线程是Consumer角色，它会通过takeRequest方法从queue中取出request。
 *
 * 使用生产者消费者模式可以将MakeStringRequest的实例和DisplayStringRequest的实例
 * 安全的从Producer角色的线程传递给Consumer角色的线程
 */
class SchedulerThread extends Thread{
    /**
     * 用于保存来自Proxy类的请求，当invoke方法被调用后，Queue中会增加一个请求(request)
     */
    private final ActivationQueue queue;

    public SchedulerThread(ActivationQueue queue) {
        this.queue = queue;
    }

    public void invoke(MethodRequest request) {
        queue.putRequest(request);
    }

    /**
     * run方法会从queue中取出一个请求(takeRequest)，然后执行(execute)该请求
     */
    @Override
    public void run() {
        while (true) {
            MethodRequest request = queue.takeRequest();
            request.excute();
        }
    }
}
