package com.uptoser.java.javase.baselib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.text.DateFormat.FULL;
import static java.text.DateFormat.MEDIUM;
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

    /**
     * DateFormat也是一个抽象类，它也提供了如下几个类方法用于获取DateFormat对象。
     * ➢ getDateInstance()：返回一个日期格式器，它格式化后的字符串只有日期，没有时间。
     * 该方法可以传入多个参数，用于指定日期样式和Locale等参数；如果不指定这些参数，则使用默认参数。
     * ➢ getTimeInstance()：返回一个时间格式器，它格式化后的字符串只有时间，没有日期。
     * 该方法可以传入多个参数，用于指定时间样式和Locale等参数；如果不指定这些参数，则使用默认参数。
     * ➢ getDateTimeInstance()：返回一个日期、时间格式器，它格式化后的字符串既有日期，也有时间。
     * 该方法可以传入多个参数，用于指定日期样式、时间样式和Locale等参数；如果不指定这些参数，则使用默认参数。
     */
    private void dateFormat() throws ParseException {
//需要被格式化的时间
        Date dt = new Date();
        //创建两个Locale，分别代表中国、美国
        Locale[] locales = {Locale.CHINA, Locale.US};
        DateFormat[] df = new DateFormat[16];
        //为上面两个Locale创建16个DateFormat对象
        for (int i = 0 ; i < locales.length ; i++)
        {
            df[i * 8] = DateFormat.getDateInstance(SHORT, locales[i]);
            df[i * 8 + 1] = DateFormat.getDateInstance(MEDIUM, locales[i]);
            df[i * 8 + 2] = DateFormat.getDateInstance(LONG, locales[i]);
            df[i * 8 + 3] = DateFormat.getDateInstance(FULL, locales[i]);
            df[i * 8 + 4] = DateFormat.getTimeInstance(SHORT, locales[i]);
            df[i * 8 + 5] = DateFormat.getTimeInstance(MEDIUM , locales[i]);
            df[i * 8 + 6] = DateFormat.getTimeInstance(LONG , locales[i]);
            df[i * 8 + 7] = DateFormat.getTimeInstance(FULL , locales[i]);
        }
        for (int i = 0 ; i < locales.length ; i++)
        {
            switch (i)
            {
                case 0:
                    System.out.println("-------中国日期格式--------");
                    break;
                case 1:
                    System.out.println("-------美国日期格式--------");
                    break;
            }
            System.out.println("SHORT格式的日期格式："
                    + df[i * 8].format(dt));
            System.out.println("MEDIUM格式的日期格式："
                    + df[i * 8 + 1].format(dt));
            System.out.println("LONG格式的日期格式："
                    + df[i * 8 + 2].format(dt));
            System.out.println("FULL格式的日期格式："
                    + df[i * 8 + 3].format(dt));
            System.out.println("SHORT格式的时间格式："
                    + df[i * 8 + 4].format(dt));
            System.out.println("MEDIUM格式的时间格式："
                    + df[i * 8 + 5].format(dt));
            System.out.println("LONG格式的时间格式："
                    + df[i * 8 + 6].format(dt));
            System.out.println("FULL格式的时间格式："
                    + df[i * 8 + 7].format(dt));
        }

        String str1 = "2007-12-12";
        String str2 = "2007年12月10日";
        //下面输出 Wed Dec 12 00:00:00 CST 2007
        System.out.println(DateFormat.getDateInstance().parse(str1));
        //下面输出 Mon Dec 10 00:00:00 CST 2007
        System.out.println(DateFormat.getDateInstance(LONG).parse(str2));
        //下面抛出 ParseException异常
        System.out.println(DateFormat.getDateInstance().parse(str2));
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
    public static void main(String[] args) throws ParseException {
        new DateMain().dateTest().calendarTest();
        //SimpleDateFormat 创建SimpleDateFormat对象时需要传入一个pattern字符串 是一个日期模板字符串
        Date d = new Date();
        //创建一个SimpleDateFormat对象
        SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy年中第D天");
        //将d格式化成日期，输出：公元2007年中第354天
        String dateStr = sdf1.format(d);
        System.out.println(dateStr);
        //一个非常特殊的日期字符串
        String str = "07###三月##21";
        SimpleDateFormat sdf2 = new SimpleDateFormat("y###MMM##d");
        //将日期字符串解析成日期，输出：Wed Mar 21 00:00:00 CST 2007
        System.out.println(sdf2.parse(str));
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf3.format(d));

        /*
        Letter	Date or Time Component	Presentation	Examples
            G	Era designator	Text	AD
            y	Year	Year	1996; 96
            Y	Week year	Year	2009; 09
            M	Month in year (context sensitive)	Month	July; Jul; 07
            L	Month in year (standalone form)	Month	July; Jul; 07
            w	Week in year	Number	27
            W	Week in month	Number	2
            D	Day in year	Number	189
            d	Day in month	Number	10
            F	Day of week in month	Number	2
            E	Day name in week	Text	Tuesday; Tue
            u	Day number of week (1 = Monday, ..., 7 = Sunday)	Number	1
            a	Am/pm marker	Text	PM
            H	Hour in day (0-23)	Number	0
            k	Hour in day (1-24)	Number	24
            K	Hour in am/pm (0-11)	Number	0
            h	Hour in am/pm (1-12)	Number	12
            m	Minute in hour	Number	30
            s	Second in minute	Number	55
            S	Millisecond	Number	978
            z	Time zone	General time zone	Pacific Standard Time; PST; GMT-08:00
            Z	Time zone	RFC 822 time zone	-0800
            X	Time zone	ISO 8601 time zone	-08; -0800; -08:00
         */
    }
}
