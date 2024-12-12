package com.uptoser.java.javase.baselib;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

public class DateMain {
    public DateMain dateTest(){
        /*
        Date类提供了6个构造器，其中4个已经Deprecated（Java不再推荐使用，使用不再推荐的构造器时编译器会提出警告信息，并导致程序性能、安全性等方面的问题），剩下的两个构造器如下。
        ➢ Date()：生成一个代表当前日期时间的Date对象。该构造器在底层调用System.currentTimeMillis()获得long整数作为日期参数。
        ➢ Date(long date)：根据指定的long型整数来生成一个Date对象。该构造器的参数表示创建的Date对象和GMT 1970年1月1日00：00：00之间的时间差，以毫秒作为计时单位。
        与Date构造器相同的是，Date对象的大部分方法也Deprecated了，剩下为数不多的几个方法。
        ➢ boolean after(Date when)：测试该日期是否在指定日期when之后。
        ➢ boolean before(Date when)：测试该日期是否在指定日期when之前。
        ➢ long getTime()：返回该时间对应的long型整数，即从GMT 1970-01-01 00：00：00到该Date对象之间的时间差，以毫秒作为计时单位。
        ➢ void setTime(long time)：设置该Date对象的时间
         */
        Date d1 = new Date();
        //获取当前时间之后100ms的时间
        Date d2 = new Date(System.currentTimeMillis() + 100);
        System.out.println(d2);
        System.out.println(d1.compareTo(d2));
        System.out.println(d1.before(d2));
        return this;
    }
    public void calendarTest(){
        /*
        Calendar类本身是一个抽象类，它是所有日历类的模板，并提供了一些所有日历通用的方法；
        但它本身不能直接实例化，程序只能创建Calendar子类的实例
         */
        //创建一个默认的Calendar
        Calendar c = Calendar.getInstance();
        // 取出年
        System.out.println(c.get(YEAR));
        // 取出月份
        System.out.println(c.get(MONTH));
        // 取出日
        System.out.println(c.get(DATE));
        // 分别设置年、月、日、小时、分钟、秒
        c.set(2003 , 10 , 23 , 12, 32, 23); //2003-11-23 12:32:23
        System.out.println(c.getTime());//取出Date对象
        // 将Calendar的年前推1年
        c.add(YEAR , -1); //2002-11-23 12:32:23
        System.out.println(c.getTime());
        // 将Calendar的月前推8个月
        c.roll(MONTH , -8); //2002-03-23 12:32:23
        System.out.println(c.getTime());

        Calendar cal1 = Calendar.getInstance();
        cal1.set(2003, 7, 23, 0, 0 , 0); //2003-8-23
        cal1.add(MONTH, 6); //2003-8-23 => 2004-2-23
        System.out.println(cal1.getTime());

        Calendar cal2 = Calendar.getInstance();
        cal2.set(2003, 7, 31, 0, 0 , 0); //2003-8-31
        // 因为进位到后月份改为2月，2月没有31日，自动变成29日
        cal2.add(MONTH, 6); //2003-8-31 => 2004-2-29
        System.out.println(cal2.getTime());

        Calendar cal3 = Calendar.getInstance();
        cal3.set(2003, 7, 23, 0, 0 , 0); //2003-8-23
        // MONTH字段“进位”，但YEAR字段并不增加
        cal3.roll(MONTH, 6); //2003-8-23 => 2003-2-23
        System.out.println(cal3.getTime());

        Calendar cal4 = Calendar.getInstance();
        cal4.set(2003, 7, 31, 0, 0 , 0); //2003-8-31
        // MONTH字段“进位”后变成2，2月没有31日，
        // YEAR字段不会改变，2003年2月只有28天
        cal4.roll(MONTH, 6); //2003-8-31 => 2003-2-28
        System.out.println(cal4.getTime());
    }

    /**
     * 当Calendar处于non-lenient模式时，如果为某个时间字段设置的值超出了它允许的取值范围，程序将会抛出异常
     */
    public void lenientTest(){
        Calendar cal = Calendar.getInstance();
        //结果是YEAR字段加1，MONTH字段为1（二月）
        cal.set(MONTH , 13);   //①
        System.out.println(cal.getTime());
        //关闭容错性
        cal.setLenient(false);
        //导致运行时异常
        cal.set(MONTH , 13);   //②
        System.out.println(cal.getTime());
    }

    /**
     * 采用延迟修改的优势是多次调用set()不会触发多次不必要的计算
     */
    public void lazyTest(){
        Calendar cal = Calendar.getInstance();
        cal.set(2003 , 7 , 31);  //2003-8-31
        // 将月份设为9，但9月31日不存在。
        // 如果立即修改，系统将会把cal自动调整到10月1日。
        cal.set(MONTH , 8);
        //下面代码输出10月1日
        //System.out.println(cal.getTime());    //①
        //设置DATE字段为5
        cal.set(DATE , 5);    //②
        System.out.println(cal.getTime());    //③
    }

    /*
    Java 8专门新增了一个java.time包，该包下包含了如下常用的类。
    ➢ Clock：该类用于获取指定时区的当前日期、时间。该类可取代System类的currentTimeMillis()方法，而且提供了更多方法来获取当前日期、时间。该类提供了大量静态方法来获取Clock对象。
    ➢ Duration：该类代表持续时间。该类可以非常方便地获取一段时间。
    ➢ Instant：代表一个具体的时刻, 可以精确到纳秒。该类提供了静态的now()方法来获取当前时刻, 也提供了静态的now(Clock clock)方法来获取clock对应的时刻。除此之外，它还提供了一系列minusXxx()方法在当前时刻基础上减去一段时间，也提供了plusXxx()方法在当前时刻基础上加上一段时间。
    ➢ LocalDate：该类代表不带时区的日期, 例如2007-12-03。该类提供了静态的now()方法来获取当前日期, 也提供了静态的now(Clock clock)方法来获取clock对应的日期。除此之外，它还提供了minusXxx()方法在当前年份基础上减去几年、几月、几周或几日等，也提供了plusXxx()方法在当前年份基础上加上几年、几月、几周或几日等。
    ➢ LocalTime：该类代表不带时区的时间, 例如10：15：30。该类提供了静态的now()方法来获取当前时间, 也提供了静态的now(Clock clock)方法来获取clock对应的时间。除此之外，它还提供了minusXxx()方法在当前年份基础上减去几小时、几分、几秒等，也提供了plusXxx()方法在当前年份基础上加上几小时、几分、几秒等。
    ➢ LocalDateTime：该类代表不带时区的日期、时间, 例如2007-12-03T10：15：30。该类提供了静态的now()方法来获取当前日期、时间, 也提供了静态的now(Clock clock)方法来获取clock对应的日期、时间。除此之外，它还提供了minusXxx()方法在当前年份基础上减去几年、几月、几日、几小时、几分、几秒等，也提供了plusXxx()方法在当前年份基础上加上几年、几月、几日、几小时、几分、几秒等。
    ➢ MonthDay：该类仅代表月日, 例如--04-12。该类提供了静态的now()方法来获取当前月日, 也提供了静态的now(Clock clock)方法来获取clock对应的月日。
    ➢ Year：该类仅代表年, 例如2014。该类提供了静态的now()方法来获取当前年份, 也提供了静态的now(Clock clock)方法来获取clock对应的年份。除此之外，它还提供了minusYears()方法在当前年份基础上减去几年，也提供了plusYears()方法在当前年份基础上加上几年。
    ➢ YearMonth：该类仅代表年月, 例如2014-04。该类提供了静态的now()方法来获取当前年月, 也提供了静态的now(Clock clock)方法来获取clock对应的年月。除此之外，它还提供了minusXxx()方法在当前年月基础上减去几年、几月，也提供了plusXxx()方法在当前年月基础上加上几年、几月。
    ➢ ZonedDateTime：该类代表一个时区化的日期、时间。
    ➢ ZoneId：该类代表一个时区。
    ➢ DayOfWeek：这是一个枚举类，定义了周日到周六的枚举值。
    ➢ Month：这也是一个枚举类，定义了一月到十二月的枚举值。
     */
    public static void main(String[] args) {
        new DateMain().dateTest().calendarTest();
    }
}
