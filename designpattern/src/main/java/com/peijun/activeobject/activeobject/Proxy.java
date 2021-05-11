package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:10
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * Proxy类会被MakerClientThread类和DisplayClientThread类调用，
 * 其任务在于方法的调用转换为对象（实例）
 * <p>
 * Proxy类不是public的，无法从包外访问它，但是它是ActiveObject接口的实现类，
 * 所以可以将其当做ActiveObject对象，从包外访问它
 */
class Proxy implements ActiveObject {
    private final SchedulerThread scheduler;
    private final Servant servant;

    public Proxy(SchedulerThread scheduler, Servant servant) {
        this.scheduler = scheduler;
        this.servant = servant;
    }

    /**
     * 该方法利用接收到的参数创建了一个MakeStringRequest的实例
     * 接着将这个实例传递给了scheduler的invoke方法
     * 由于方法的调用被转换为了实例，所以可以被存放到队列中。这与Command模式的思想很类似
     * <p>
     * 实际上这就是 调用与执行的分离
     * Proxy类相当于方法的调用，因为他不会执行方法，所以Proxy的makeString方法会立即返回
     *
     * @param count
     * @param filechar
     * @return 返回值是FutureResult的实例，这里使用了Future模式
     */
    @Override
    public Result<String> makeString(int count, char filechar) {
        FutureResult<String> future = new FutureResult<>();
        scheduler.invoke(new MakeStringRequest(servant, future, count, filechar));
        return future;
    }

    /**
     * 创建一个DisplayStringRequest实例，然后将其传递给了scheduler的invoke方法
     * 由于没有返回值，所以无需使用Future模式
     *
     * @param string
     */
    @Override
    public void displayString(String string) {
        scheduler.invoke(new DisplayStringRequest(servant, string));
    }
}
