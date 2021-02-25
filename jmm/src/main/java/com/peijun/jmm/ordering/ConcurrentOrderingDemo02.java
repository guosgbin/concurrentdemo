package com.peijun.jmm.ordering;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.II_Result;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * 测试有序性
 */
@JCStressTest
@Outcome(id = {"0, 1", "1, 0", "1, 1"}, expect = Expect.ACCEPTABLE, desc = "NOT ORDERING")
@Outcome(id = {"0, 0"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "HAD ORDERING")
@State
public class ConcurrentOrderingDemo02 {
    private volatile int a = 0, b = 0;

    @Actor
    public void actor1(II_Result r) {
        Thread one = new Thread(() -> {
            a = 1;
            r.r1 = b;
        });
        Thread two = new Thread(() -> {
            b = 1;
            r.r2 = a;
        });
        two.start();
        one.start();
        try {
            // 先执行线程1 再执行线程2
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
