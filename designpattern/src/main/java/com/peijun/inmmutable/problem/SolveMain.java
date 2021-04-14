package com.peijun.inmmutable.problem;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author: Dylan kwok GSGB
 * @date: 2021/4/13 22:40
 * <p>
 * 古之立大事者，不惟有超世之才，亦必有坚忍不拔之志——苏轼
 *
 * 测试类
 */
public class SolveMain {
    public static void main(String[] args) {
        testGetterProblem();
//        testConstructorProblem();
    }

    /**
     * 测试Getter方法的问题
     */
    private static void testGetterProblem() {
        LocalDate localDate = LocalDate.of(1997, Month.JULY, 8);
        Date birthday = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // 创建一个 人 的对象
        SolvePerson person = new SolvePerson(130,24,"培珺", birthday);
        System.out.println(person);
        // 修改出生日期
        Date date = person.getBirthday();
        date.setTime(new Date().getTime());// 出生日期设置为当前时间
        System.out.println(person);
    }

    /**
     * 测试构造方法的问题
     */
    private static void testConstructorProblem() {
        LocalDate localDate = LocalDate.of(1997, Month.JULY, 8);
        Date birthday = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // 创建一个 人 的对象
        SolvePerson person = new SolvePerson(130,24,"培珺", birthday);
        System.out.println(person);

        birthday.setTime(new Date().getTime());// 出生日期设置为当前时间
        System.out.println(person);
    }
}
