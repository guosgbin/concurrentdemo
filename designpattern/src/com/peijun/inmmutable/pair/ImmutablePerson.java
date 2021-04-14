package com.peijun.inmmutable.pair;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/14 8:14
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 不可变的Person类
 */
public final class ImmutablePerson {
    private final String name;
    private final String address;

    public ImmutablePerson(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public ImmutablePerson(MutablePerson mutablePerson) {
        synchronized (mutablePerson) {
            this.name = mutablePerson.getName();
            this.address = mutablePerson.getAddress();
        }
    }

    public MutablePerson getImmutablePerson() {
        return new MutablePerson(this);
    }

    // 仅让同一个包下的Immutable调用
    public String getName() {
        return name;
    }

    // 仅让同一个包下的Immutable调用
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
