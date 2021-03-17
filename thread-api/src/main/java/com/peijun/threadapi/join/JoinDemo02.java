package com.peijun.threadapi.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/3/17 22:38
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 * <p>
 * Join的实例
 */
public class JoinDemo02 {
    private static List<String> flightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> results = search("SH", "BJ");
        System.out.println("=================");
        results.forEach(System.out::println);
    }

    private static List<String> search(String original, String dest) {
        final List<String> result = new ArrayList<>();
        List<QueryResultTask> tasks = flightCompany.stream()
                .map(f -> createSearchTask(f, original, dest))
                .collect(Collectors.toList());
        // 开启线程
        tasks.forEach(Thread::start);
        // 等待每个线程执行完毕
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });
        // 遍历所有的结果 放到result中返回
        tasks.stream().map(QueryResultTask::get).forEach(result::addAll);
        return result;
    }

    private static QueryResultTask createSearchTask(String fight, String original, String dest) {
        return new QueryResultTask(fight, original, dest);
    }
}
