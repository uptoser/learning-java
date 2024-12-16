package com.uptoser.java.javase.java8.datetime;

import java.time.*;
import java.time.temporal.ChronoField;

/**
 * Java 8专门新增了一个java.time包，该包下包含了如下常用的类
 * ➢ Clock：该类用于获取指定时区的当前日期、时间。该类可取代System类的currentTimeMillis()方法，而且提供了更多方法来获取当前日期、时间。
 * 该类提供了大量静态方法来获取Clock对象。
 * ➢ Duration：该类代表持续时间。该类可以非常方便地获取一段时间。
 * ➢ Instant：代表一个具体的时刻, 可以精确到纳秒。该类提供了静态的now()方法来获取当前时刻, 也提供了静态的now(Clock clock)方法来获取clock对应的时刻。
 * 除此之外，它还提供了一系列minusXxx()方法在当前时刻基础上减去一段时间，也提供了plusXxx()方法在当前时刻基础上加上一段时间。
 * ➢ LocalDate：该类代表不带时区的日期, 例如2007-12-03。该类提供了静态的now()方法来获取当前日期, 也提供了静态的now(Clock clock)方法来获取clock对应的日期。
 * 除此之外，它还提供了minusXxx()方法在当前年份基础上减去几年、几月、几周或几日等，也提供了plusXxx()方法在当前年份基础上加上几年、几月、几周或几日等。
 * ➢ LocalTime：该类代表不带时区的时间, 例如10：15：30。该类提供了静态的now()方法来获取当前时间, 也提供了静态的now(Clock clock)方法来获取clock对应的时间。
 * 除此之外，它还提供了minusXxx()方法在当前年份基础上减去几小时、几分、几秒等，也提供了plusXxx()方法在当前年份基础上加上几小时、几分、几秒等。
 * ➢ LocalDateTime：该类代表不带时区的日期、时间, 例如2007-12-03T10：15：30。该类提供了静态的now()方法来获取当前日期、时间,
 * 也提供了静态的now(Clock clock)方法来获取clock对应的日期、时间。除此之外，它还提供了minusXxx()方法在当前年份基础上减去几年、几月、几日、几小时、几分、几秒等，
 * 也提供了plusXxx()方法在当前年份基础上加上几年、几月、几日、几小时、几分、几秒等。
 * ➢ MonthDay：该类仅代表月日, 例如--04-12。该类提供了静态的now()方法来获取当前月日, 也提供了静态的now(Clock clock)方法来获取clock对应的月日。
 * ➢ Year：该类仅代表年, 例如2014。该类提供了静态的now()方法来获取当前年份, 也提供了静态的now(Clock clock)方法来获取clock对应的年份。
 * 除此之外，它还提供了minusYears()方法在当前年份基础上减去几年，也提供了plusYears()方法在当前年份基础上加上几年。
 * ➢ YearMonth：该类仅代表年月, 例如2014-04。该类提供了静态的now()方法来获取当前年月, 也提供了静态的now(Clock clock)方法来获取clock对应的年月。
 * 除此之外，它还提供了minusXxx()方法在当前年月基础上减去几年、几月，也提供了plusXxx()方法在当前年月基础上加上几年、几月。
 * ➢ ZonedDateTime：该类代表一个时区化的日期、时间。
 * ➢ ZoneId：该类代表一个时区。
 * ➢ DayOfWeek：这是一个枚举类，定义了周日到周六的枚举值。
 * ➢ Month：这也是一个枚举类，定义了一月到十二月的枚举值。
 */
public class Application {

    public static void main(String[] args) {
        //获取当前Clock
        Clock clock = Clock.systemUTC();
        //获取clock对应的毫秒数，与System.currentTimeMillis()相同
        System.out.println(clock.millis()+"--"+System.currentTimeMillis());
        Instant instant = clock.instant();
        System.out.println("当前时刻为："+ instant);
        //java.time.Instant类对时间建模的方式，基本上它是以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始所经历的秒数进行计算
        Instant now = Instant.now();
        System.out.println("当前时间为："+now);
        now = Instant.ofEpochSecond(3);
        Instant.ofEpochSecond(3, 0);
        Instant.ofEpochSecond(2, 1_000_000_000); //2秒之后再加上10亿纳秒（1 秒）
        Instant.ofEpochSecond(4, -1_000_000_000); //4秒之前的10亿纳秒（1 秒）
        System.out.println(now);
        now.plusSeconds(600);//加600秒
        Instant instant1 = Instant.parse("2024-12-12T14:59:30.733Z");//根据字符串解析Instant对象
        instant.plus(Duration.ofDays(1).plusHours(11));//加上1天11小时
        System.out.println("----------------------------");
        Duration duration = Duration.ofSeconds(6000);
        System.out.println(duration.toHours());//6000秒相当于多少小时
        System.out.println(duration.toDays());//6000秒相当于多少天
        Clock offset = Clock.offset(clock, duration);
        System.out.println("clock+6000秒是："+offset.instant());
        System.out.println("----------------------------");
        //创建一个LocalDate对象并读取其值
        LocalDate date = LocalDate.of(2017, Month.SEPTEMBER, 21);
        //你还可以使用工厂方法now从系统时钟中获取当前的日期：
        date = LocalDate.now();
        System.out.println(date);
        int year1 = date.get(ChronoField.YEAR);
        int month2 = date.get(ChronoField.MONTH_OF_YEAR);
        int day3 = date.getDayOfMonth();
        System.out.println(year1+"/"+month2+"/"+day3);
        System.out.println(LocalDate.ofYearDay(2008,360));//返回2008年的第360天
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
        //获取当前年份
        Year year = Year.now();
        System.out.println(year);
        //根据指定月份获取年月
        YearMonth yearMonth = year.atMonth(Month.OCTOBER);
        System.out.println(yearMonth);

    }

}
