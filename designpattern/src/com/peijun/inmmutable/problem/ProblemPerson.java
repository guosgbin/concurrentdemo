package com.peijun.inmmutable.problem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/13 22:32
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 有问题的 不可变类
 */
public class ProblemPerson {
    // 重量
    private final double weight;
    // 年龄
    private final Integer age;
    // 字
    private final String name;
    // 生日
    private final Date birthday;

    public ProblemPerson(double weight, Integer age, String name, Date birthday) {
        this.weight = weight;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }

    public double getWeight() {
        return weight;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "ProblemPerson{" +
                "weight=" + weight +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() +
                '}';
    }
}
