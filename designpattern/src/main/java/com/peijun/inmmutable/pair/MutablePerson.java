package com.peijun.inmmutable.pair;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/14 8:15
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 可变的Person类
 */
public class MutablePerson {
    private String name;
    private String address;

    public MutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public MutablePerson(ImmutablePerson immutablePerson) {
        this.name = immutablePerson.getName();
        this.address = immutablePerson.getAddress();
    }

    public synchronized void setPerson(String newName, String newAddress) {
        this.name = newName;
        this.address = newAddress;
    }

    public synchronized ImmutablePerson getImmutablePerson() {
        return new ImmutablePerson(this);
    }

    // 仅让同一个包下的Immutable调用
    String getName() {
        return name;
    }

    // 仅让同一个包下的Immutable调用
    String getAddress() {
        return address;
    }

    @Override
    public synchronized String toString() {
        return "MutablePerson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
