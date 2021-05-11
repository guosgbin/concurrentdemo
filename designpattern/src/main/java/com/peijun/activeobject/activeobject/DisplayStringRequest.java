package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:28
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 此类表示“显示字符串”的类
 * 该类对应ActiveObject接口的displayString方法。
 * 实际上创建DisplayStringRequest的实例时Proxy类的displayString方法
 */
class DisplayStringRequest extends MethodRequest<Object> {

    private final String string;

    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }

    /**
     * 调用servant的displayString方法
     * 与MakeStringRequest类的一个很大的区别是：displayString方法没有返回值，
     * 正是因为没有返回值，所以这里没有使用Future模式
     */
    @Override
    public void excute() {
        servant.displayString(string);
    }
}
