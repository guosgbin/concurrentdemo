package com.peijun.activeobject;

import com.peijun.activeobject.activeobject.ActiveObject;
import com.peijun.activeobject.activeobject.ActiveObjectFactory;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/5/10 23:46
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread("Alice", activeObject).start();
        new MakerClientThread("Bobby", activeObject).start();
        new MakerClientThread("Chris", activeObject).start();
    }
}
