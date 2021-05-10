package com.peijun.activeobject.activeobject;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:28
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
class DisplayStringRequest extends MethodRequest<Object> {

    private final String string;

    public DisplayStringRequest(Servant servant, String string) {
        super(servant, null);
        this.string = string;
    }

    @Override
    public void excute() {
        servant.displayString(string);
    }
}
