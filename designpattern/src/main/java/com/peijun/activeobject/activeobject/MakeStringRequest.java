package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:24
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 表示生成字符串的类
 * 此类与ActiveObject接口的makeString方法相对应
 * 实际上创建MakeStringRequest的实例时Proxy类的makeString方法
 *
 * MakeStringRequest类是以类来表示的ActiveObject接口的makeString方法。那么到底如何将方法表示为类呢？
 * 下面让我们仔细看一下其中的对应关系，具体如下所示。
 * 1.makeString方法的参数与MakeStringRequest的实例字段对应。
 * 2.makeString方法的调用与MakeStringRequest的实例的创建和SchedulerThread的invoke方法的调用对应。
 * 3.makeString方法的执行与Servant的makeString方法的调用对应。
 * 4.makeString方法的返回值与通过setResult向future设置的result对应。
 */
class MakeStringRequest extends MethodRequest<String> {

    private final int count;
    private final char fillchar;

    public MakeStringRequest(Servant servant, FutureResult<String> future, int count, char fillchar) {
        super(servant, future);
        this.count = count;
        this.fillchar = fillchar;
    }

    /**
     * 会调用servant的makeString方法，这与“执行”请求相对应
     * 调用future的setResult与“设置请的返回值”相对应
     */
    @Override
    public void excute() {
        Result<String> result = servant.makeString(count, fillchar);
        future.setResult(result);
    }
}
