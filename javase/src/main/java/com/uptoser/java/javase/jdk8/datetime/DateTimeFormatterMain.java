package com.uptoser.java.javase.jdk8.datetime;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Arrays;

/**
 * DateTimeFormatter时间格式化工具
 */
public class DateTimeFormatterMain {
    public static void main(String[] args) {
        DateTimeFormatter[] dateTimeFormatters = {
                //直接使用常量创建DateTimeFormatter
                DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                //使用本地化的不同风格来创建 DateTimeFormatter 格式器
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,FormatStyle.MEDIUM),
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG,FormatStyle.SHORT),
                DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG),
                DateTimeFormatter.ofPattern("Gyyyy年MM月dd日 HH:mm:ss")
        };
        LocalDateTime now = LocalDateTime.now();
        //依次使用不同的格式器对 LocalDateTime 进行格式化
        Arrays.stream(dateTimeFormatters).forEach(f-> System.out.println(f.format(now)));//也可以使用now.format(f)

        System.out.println(LocalDateTime.parse("2008-02-24 18:09:30",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).get(ChronoField.YEAR));
    }
}
