package com.peijun.jmm.ordering;

import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.IIII_Result;
import org.openjdk.jcstress.infra.results.II_Result;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/2/21 19:05
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * 测试有序性
 */
@JCStressTest
@Outcome(id = {"0, 1", "2, 0", "1, 2"}, expect = Expect.ACCEPTABLE, desc = "okKKKKKKKKKKKKKK")
@Outcome(id = {"0, 0", "2, 1"}, expect = Expect.ACCEPTABLE_INTERESTING, desc = "dangerRRRRRRRRRR")
@State
public class ConcurrentOrderingDemo {
    private int a = 0, b = 0;
    private int i, j;

    @Actor
    public void actor1(I_Result r) {
//        a = 1;
//        i = b;
//        r.r1 = a;
//        r.r2 = i;
        a = 1;
        r.r1 = b;
    }
    @Actor
    public void actor2(I_Result r) {
//        b = 1;
//        j = a;
//        r.r3 = b;
//        r.r4 = j;
        b = a;
    }
}
