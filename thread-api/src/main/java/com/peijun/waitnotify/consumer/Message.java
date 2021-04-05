package com.peijun.waitnotify.consumer;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/5 22:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 */
public class Message<T> {
    private Integer id; // id
    private T data; // 数据

    public Message(Integer id, T data) {
        this.id = id;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
