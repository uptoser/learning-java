package com.uptoser.java.javase.jdk8.datetime;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

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

    /**
     * LocalDate和LocalTime
     */
    @Test
    public void test1() {
        /* *****************LocalDate和LocalTime**************** */
        //创建一个LocalDate对象并读取其值
        LocalDate localDate = LocalDate.of(2017, Month.SEPTEMBER, 21);
        System.out.println(localDate);
        int year1 = localDate.get(ChronoField.YEAR); //使用TemporalField读取LocalDate的值
        Month month = localDate.getMonth(); // SEPTEMBER
        int month2 = localDate.get(ChronoField.MONTH_OF_YEAR); //使用TemporalField读取LocalDate的值
        int day3 = localDate.getDayOfMonth();
        DayOfWeek week = localDate.getDayOfWeek(); // THURSDAY
        int len = localDate.lengthOfMonth();   // 30 (days in September)
        boolean leap = localDate.isLeapYear(); // false (not a leap year)
        System.out.println(year1 + "/" + month2 + "/" + day3);
        //你还可以使用工厂方法now从系统时钟中获取当前的日期：
        localDate = LocalDate.now();
        System.out.println(localDate);
        //返回2008年的第360天
        LocalDate oyd = LocalDate.ofYearDay(2008, 360);
        System.out.println(oyd);
        System.out.println("------------------------------");
        //创建一个LocalTime对象来表示一天中的时间，比如13:45:20
        LocalTime localTime = LocalTime.of(13, 45, 20);
        int hour = localTime.getHour();  // 13
        int minute = localTime.getMinute(); //13
        int second = localTime.getSecond();  //20
        System.out.println(hour + ":" + minute + ":" + second);

    }

    /**
     * LocalDateTime合并日期和时间
     */
    @Test
    public void test2() {
        /* ****************LocalDateTime合并日期和时间***************** */
        /*
        LocalDate和LocalTime都可以通过解析代表它们的字符串创建。
        你可以向parse方法传递一个DateTimeFormatter
         */
        LocalDate date = LocalDate.parse("2017-09-21");
        LocalTime time = LocalTime.parse("13:45:20");
        //这个复合类名叫LocalDateTime，是LocalDate和LocalTime的合体
        Arrays.asList(
                LocalDateTime.of(2014, Month.SEPTEMBER, 21, 13, 45, 20),
                LocalDateTime.of(date, time),
                date.atTime(13, 45, 20),
                date.atTime(time),
                time.atDate(date)
        ).forEach(System.out::println);
        System.out.println("----------------------------------");
        //从LocalDateTime中提取LocalDate或者LocalTime
        LocalDateTime now = LocalDateTime.now();
        LocalDate date1 = now.toLocalDate();
        LocalTime time1 = now.toLocalTime();
        System.out.printf("date1:%s , time1:%s\n", date1, time1);
        System.out.println("----------------------------------");
        /* ****************修改**************** */
        //withAttribute方法会创建对象的一个副本，并按照需要修改它的属性
        date1 = LocalDate.of(2017, 9, 21);  //2017-09-21
        LocalDate date2 = date1.withYear(2011); //2011-09-21
        LocalDate date3 = date2.withDayOfMonth(25);   //2011-09-21
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 2); // 2011-02-25
        //以相对方式修改LocalDate对象的属性
        LocalDate date5 = date4.plus(6, ChronoUnit.MONTHS);//2011-08-25
        LocalDate date6 = date5.minusYears(6);//2005-08-25
        //使用TemporalAdjusters
        LocalDate date7 = date6.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));//2005-08-28 下个周末
        LocalDate date8 = date7.with(TemporalAdjusters.lastDayOfMonth());//2005-08-31 上个月底
        System.out.println(date8);
        System.out.println("----------------------------------");

    }

    /**
     * 机器的日期和时间格式(时刻)
     * 从计算机的角度来看，建模时间最自然的格式是表示一个持续时间段上某个点的单一大整型数。
     * 这也是新的java.time.Instant类对时间建模的方式，
     * 基本上它是以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始所经历的秒数进行计算
     */
    @Test
    public void test3() {
        //传递代表秒数的值创建一个该类的实例，他的第二个参数值是以纳秒为单位的
        Instant utc3s = Instant.ofEpochSecond(3);
        utc3s = Instant.ofEpochSecond(2, 1_000_000_000); //2秒之后再加上10亿纳秒（1 秒）
        System.out.println("UTC时间3秒后："+utc3s);
        Instant utc1m3s = utc3s.plusSeconds(60);//加60秒
        System.out.println("当前时间1分3秒后：" + utc1m3s);
        //Instant类也支持静态工厂方法now
        Instant now = Instant.now();
        System.out.println("当前时刻为：" + now);
        Instant instant = Instant.parse("2024-12-12T14:59:30.733Z");//根据字符串解析Instant对象
        instant.plus(Duration.ofDays(1).plusHours(11));//加上1天11小时
    }

    /**
     * 时间日期计算(间隔)
     * 你看到的所有类都实现了Temporal接口，Temporal接口定义了如何读取和操纵为时间建模的对象的值
     * 由于LocalDateTime和Instant是为不同的目的而设计的，一个是为了便于人阅读使用，另一个是为了便于机器处理，
     * 因此不能将二者混用。如果你试图在这两类对象之间创建Duration，就会触发一个DateTimeException异常
     */
    @Test
    public void test4() {
        /* ****************Duration计算时间************** */
        LocalTime time1 = LocalTime.parse("13:45:20");
        LocalTime time2 = LocalTime.parse("13:46:20");
        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(0);
        Duration du1 = Duration.between(time1, time2);
        Duration du2 = Duration.between(instant1, instant2);
        System.out.printf("du1 = %s, du2 = %s\n",du1,du2);
        /* ****************Period计算日期************** */
        LocalDate da1 = LocalDate.of(2017, 9, 11);
        LocalDate da2 = LocalDate.of(2017, 9, 21);
        Period tenDays = Period.between(da1,da2);
        //间隔相加
        Duration plus = du1.plus(du2);
        System.out.println(plus);
        System.out.println(tenDays);
        System.out.println("----------------------------");
        //创建Duration和Period对象
        Duration threeMinutes = Duration.ofMinutes(3);//3分钟
        threeMinutes = Duration.of(3, ChronoUnit.MINUTES);//3分钟
        Duration duration = Duration.ofSeconds(6000);//6000秒
        System.out.println("6000秒相当于"+duration.toHours()+"小时");//6000秒相当于多少小时
        Period nineDays = Period.ofDays(9);//9天
        Period threeWeeks = Period.ofWeeks(3);//3周
        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println("----------------------------");
    }

    @Test
    public void test5() {
        //获取当前Clock
        Clock clock = Clock.systemUTC();
        //获取clock对应的毫秒数，与System.currentTimeMillis()相同
        System.out.println(clock.millis() + "--" + System.currentTimeMillis());
        Instant instant = clock.instant();
        System.out.println("当前时刻为：" + instant);
        Duration duration = Duration.ofSeconds(6000);//6000秒
        Clock offset = Clock.offset(clock, duration);
        System.out.println("clock+6000秒是：" + offset.instant());

        //获取当前年份
        Year year = Year.now();
        System.out.println(year);
        //根据指定月份获取年月
        YearMonth yearMonth = year.atMonth(Month.OCTOBER);
        System.out.println(yearMonth);
    }


}
