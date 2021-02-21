package com.peijun.jmm.ordering;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/21 21:35
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
@JCStressTest
@Outcome(id = {"1","4"}, expect = Expect.ACCEPTABLE,desc = "ok")
@Outcome(id = "0",expect = Expect.ACCEPTABLE_INTERESTING,desc = "danger")
@State
public class Demo {
    int num = 0;
    boolean ready = false;
    // 线程一执行的代码
    @Actor
    public void actor1(I_Result r) {
        if(ready) {
            r.r1 = num + num;
        } else {
            r.r1 = 1;
        }
    }
    @Actor
    public void actor2(I_Result r) {
        num = 2;
        ready = true;
    }
}

