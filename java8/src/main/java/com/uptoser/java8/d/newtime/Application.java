package com.uptoser.java8.d.newtime;

import java.time.*;
import java.time.temporal.ChronoField;


//流
public class Application {

    private Application() {
    }


    public static void main(String[] args) {
        //创建一个LocalDate对象并读取其值
        LocalDate date = LocalDate.of(2017, 9, 21);
              //你还可以使用工厂方法now从系统时钟中获取当前的日期：
        date = LocalDate.now();
        int year1 = date.get(ChronoField.YEAR);
        int month2 = date.get(ChronoField.MONTH_OF_YEAR);
        int day3 = date.getDayOfMonth();
        System.out.println(year1+"-"+month2+"-"+day3);
        //一天中的时间，比如13:45:20，可以使用LocalTime类表示
        LocalTime time = LocalTime.of(13, 45, 20);
        System.out.println(time);
        //LocalDate和LocalTime都可以通过解析代表它们的字符串创建。
        date = LocalDate.parse("2017-09-21");
        time = LocalTime.parse("13:45:20");
        //这个复合类名叫LocalDateTime，是LocalDate和LocalTime的合体
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.SEPTEMBER, 21, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);
        //从LocalDateTime中提取LocalDate或者LocalTime
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();
        //java.time.Instant类对时间建模的方式，基本上它是以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始所经历的秒数进行计算
        Instant now = Instant.now();
        now = Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000); //2秒之后再加上10亿纳秒（1 秒）
        Instant.ofEpochSecond(4, -1_000_000_000); //4秒之前的10亿纳秒（1 秒）
        System.out.println(now);
        System.out.println("----------------------------");







    }

}
