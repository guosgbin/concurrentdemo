package com.peijun.future;
/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/28 21:54
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 首先创建FutureData的实例future，该实例会被作为返回值返回给调用者
 * 接着，它会启动一个新的线程并在新线程中创建RealData的实例
 * 新线程会去处理任务，处理完任务后，将任务的结果放到future对象中
 *
 * 总结起来，发送任务请求的线程会做如下三件事
 * 1.创建FutureData实例
 * 2.启动一个新线程，用于创建RealData的实例
 * 3.将FutureData的实例作为返回值返回给调用者
 */
public class Host {
    public Data request(String taskDesc) {
        System.out.println("接收到任务了");
        // 创建FutureData实例
        FutureData future = new FutureData();
        // 创建一个新线程去处理任务
        new Thread(() -> {
            // 委托给RealData处理任务
            RealData realData = new RealData(taskDesc);
            // 任务处理完成后，就将产生的realData结果设置到future中
            future.setRealData(realData);
        }).start();
        // 返回future
        return future;
    }
}
