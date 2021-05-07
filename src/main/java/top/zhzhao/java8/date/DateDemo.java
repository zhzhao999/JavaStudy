package top.zhzhao.java8.date;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;


public class DateDemo {

    /**
     * LocalDate
     * LocalTime
     * LocalDateTime
     */
    @Test
    public void test01() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);

        LocalDateTime plusDays = localDateTime.plusDays(2);
        System.out.println(plusDays);

        LocalDateTime minusMonths = localDateTime.minusMonths(1);
        System.out.println(minusMonths);
    }

    /**
     * Instant  时间戳
     */
    @Test
    public void test02() {
        Instant instant = Instant.now();
        System.out.println(instant);

        long epochMilli = instant.toEpochMilli();
        System.out.println(epochMilli);

        Instant instant1 = instant.plusSeconds(10);
        System.out.println(Duration.between(instant, instant1).toMillis());
    }

    /**
     * ZoneId  时区
     */
    @Test
    public void test03() {
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(zoneIds.size());
        zoneIds.forEach(System.out::println);
        System.out.println("-----系统时区-----");
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId.toString());
    }

    /**
     * Date Instant LocalDateTime相互转换
     */
    @Test
    public void test04() {
        Date date = new Date();
        System.out.println(date);
        Instant instant = date.toInstant();
        System.out.println(instant);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(localDateTime);

        System.out.println("-----倒转----");

        Instant instant1 = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(instant1);
        Date date1 = Date.from(instant1);
        System.out.println(date1);
    }

    /**
     * DateTimeFormatter 时间格式化类  线程安全的
     */
    @Test
    public void test05() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dtf.format(LocalDateTime.now());
        System.out.println(format);

        LocalDateTime localDateTime = LocalDateTime.parse("2021-05-06 20:30:23", dtf);
        System.out.println(localDateTime);
    }
}
