package com.rongxin.demo.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author xyb
 * @date 2020-6-10
 **/
public class DateUtil {

  /**
   * 注意这里不要把yyyyMMdd改成类似于YYYYMMDD这种格式会报错的
   */
  public static final String FORMAT1 = "yyyyMMdd";
  public static final String FORMAT8 = "yyMMdd";
  public static final String FORMAT9 = "yy-MM";
  public static final String FORMAT10 = "yyyy-MM";
  public static final String FORMAT2 = "yyyyMMddMMHHSS";
  public static final String FORMAT4 = "YYYY/MM/DD MM:HH:SS";
  public static final String FORMAT5 = "yyyy-MM-dd HH:mm:ss";
  public static final String FORMAT6 = "yyyy-MM-dd HH:mm";
  public static final String FORMAT7 = "yyyy-MM-dd";

  /**
   * 获取指定格式的当前系统时间
   *
   * @return 指定格式的系统当前时间
   */
  public static String getNowTime(){
    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT5);
    Date date = new Date();
    return sdf.format(date);
  }

  /***
   * 获取字符串格式的当前时间
   *
   * @param format 格式化类型
   * @return
   */
  public static String nowTimeToString(String format) {
    if (StringUtils.isEmpty(format)) {
      format = FORMAT5;
    }
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern(format);
    return LocalDateTime.now().format(dtf2);
  }


  public static void main(String[] args) throws Exception {
    // System.out.println(getStringByDate(new Date(), "YYYY-MM-DD HH:mm:ss"));
    // 启动netty客户端
    // NettyClient nettyClient = new NettyClient();
    // nettyClient.start();
    String year = getLastYear(FORMAT9);
    System.out.println("过去一年：" + year);
    System.out.println(Arrays.asList(getAppointMonths(year, nowTimeToString(FORMAT9), FORMAT9)));

  }


  public static String getLastYear(String formatDate) {
    SimpleDateFormat format = new SimpleDateFormat(FORMAT9);
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.MONTH, -11);
    Date m3 = c.getTime();
    return format.format(m3);
  }

  /**
   * 获取指定区间的月份
   *
   * @param beginMonth
   * @param endMonth
   * @return
   */
  public static Map<String,Object> getAppointMonths(String beginMonth, String endMonth, String format) {
    Map<String,Object> months = new HashMap<>();
    try {
      Date d1 = new SimpleDateFormat(format).parse(beginMonth);// 定义起始日期
      Date d2 = new SimpleDateFormat(format).parse(endMonth);// 定义结束日期，可以去当前月也可以手动写日期
      Calendar dd = Calendar.getInstance();// 定义日期实例
      dd.setTime(d1);// 设置日期起始时间
      while (dd.getTime().before(d2)) {// 判断是否到结束日期
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String str = sdf.format(dd.getTime());
        dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
        months.put(str,"0");
      }
      months.put(endMonth,"0");
    } catch (Exception e) {
      System.out.println("异常" + e.getMessage());
    }
    return months;
  }

  /***
   * 按照指定格式，格式化data日期
   *
   * @param date
   * @param format
   * @return
   */
  public static Date parseData(Date date, String format) {
    if (date != null) {
      Date newDate = date;
      try {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String strDate = sdf.format(date);
        newDate = sdf.parse(strDate);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return new java.sql.Date(newDate.getTime());
    }
    return null;
  }


  /**
   * 将Date类型转化为指定格式字符串
   */
  public static String getStringByDate(Date date, String format) {
    if (StringUtils.isEmpty(format)) {
      format = FORMAT5;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  /** 将指定格式的字符串转化为Date */
  public static Date getDateByString(String dateStr, String format) {
    if (StringUtils.isEmpty(format)) {
      format = FORMAT5;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return sdf.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /** 获取当前Date并转化为指定格式字符串 */
  public static String getCurrentDate(String format) {
    if (StringUtils.isEmpty(format)) {
      format = FORMAT5;
    }
    return getStringByDate(new Date(), format);
  }

  /** 将Date转化为LocalDateTime */
  public static LocalDateTime getLocalDateTime(Date date) {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  /** 比较两个Date的时间大小 */
  public static boolean getLocalDateTime(Date date, Date date2) {
    if (date.compareTo(date2) == 1) {
      return true;
    }
    return false;
  }

  /** 将LocalDateTime转化为Date */
  public static Date getDataByLocalDateTime(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  /** 将指定格式String类型日期转化为LocalDateTime */
  public static LocalDateTime getLocalDateTimeByString(String format, String localDateTime) {
    if (StringUtils.isEmpty(format)) {
      format = FORMAT5;
    }
    DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
    return LocalDateTime.parse(localDateTime, df);
  }

  /** 获取指定LocalDateTime的秒数 */
  public static Long getSecondByLocalDateTime(LocalDateTime localDateTime) {
    return localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
  }

  /** 获取指定LocalDateTime的毫秒数 */
  public static Long getMillSecondByLocalDateTime(LocalDateTime localDateTime) {
    return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }

  /** 将LocalDateTime转化为指定格式字符串 */
  public static String getStringByLocalDateTime(LocalDateTime localDateTime, String format) {
    return localDateTime.format(DateTimeFormatter.ofPattern(format));
  }

  /** 将LocalDateTime转化为默认yy-mm-dd hh:mm:ss格式字符串 */
  public static String getDefaultStringByLocalDateTime(LocalDateTime localDateTime) {
    return getStringByLocalDateTime(localDateTime, FORMAT5);
  }

  /** 获取当前LocalDateTime并转化为指定格式字符串 */
  public static String getCurrentLocalDateTime(String format) {
    if (StringUtils.isEmpty(format)) {
      format = FORMAT5;
    }
    return getStringByLocalDateTime(LocalDateTime.now(), format);
  }

  /** 获取一天的开始时间 */
  public static String getStartTime(LocalDateTime localDateTime) {
    return getDefaultStringByLocalDateTime(localDateTime.with(LocalTime.MIN));
  }

  /** 获取当天的开始时间 */
  public static String getStartTime() {
    return getDefaultStringByLocalDateTime(LocalDateTime.now());
  }

  /** 获取一天的结束时间 */
  public static String getEndTime(LocalDateTime localDateTime) {
    return getDefaultStringByLocalDateTime(localDateTime.with(LocalTime.MAX));
  }

  /** 获取当天的结束时间 */
  public static String getEndTime() {
    return getDefaultStringByLocalDateTime(LocalDateTime.now());
  }

  /** 比较两个LocalDateTime的时间大小 */
  public static boolean getCompareLocalDateTime(LocalDateTime localDateTime1,
      LocalDateTime localDateTime2) {
    if (localDateTime1.isBefore(localDateTime2)) {
      return true;
    }
    return false;
  }

  /** 计算两个指定格式String日期字符串的时间差(精确到毫秒) */
  public static Long getCompareSecondLocalDateTime(String format, String time1, String time2) {
    System.out.println("接收到的参数为:" + format + "," + time1 + "," + time2);
    LocalDateTime localDateTime1 = getLocalDateTimeByString(format, time1);
    LocalDateTime localDateTime2 = getLocalDateTimeByString(format, time2);
    if (getMillSecondByLocalDateTime(localDateTime1) > getMillSecondByLocalDateTime(
        localDateTime2)) {
      return getMillSecondByLocalDateTime(localDateTime1)
          - getMillSecondByLocalDateTime(localDateTime2);
    }
    return getMillSecondByLocalDateTime(localDateTime2)
        - getMillSecondByLocalDateTime(localDateTime1);
  }

  /** 获取两个LocalDateTime的天数差 */
  public static Long getCompareDayLocalDateTime(LocalDateTime localDateTime1,
      LocalDateTime localDateTime2) {
    if (getCompareLocalDateTime(localDateTime1, localDateTime2)) {
      Duration duration = Duration.between(localDateTime1, localDateTime2);
      return duration.toDays();
    } else {
      Duration duration = Duration.between(localDateTime2, localDateTime1);
      return duration.toDays();
    }
  }

  /** 获取两个LocalDateTime的小时差 */
  public static Long getCompareYearLocalDateTime(LocalDateTime localDateTime1,
      LocalDateTime localDateTime2) {
    if (getCompareLocalDateTime(localDateTime1, localDateTime2)) {
      Duration duration = Duration.between(localDateTime1, localDateTime2);
      return duration.toHours();
    } else {
      Duration duration = Duration.between(localDateTime2, localDateTime1);
      return duration.toHours();
    }
  }

  /**
   * 计算两个日期之间相差的天数
   *
   * @param smdate 较小的时间
   * @param bdate  较大的时间
   * @return 相差天数
   * @throws ParseException
   */
  public static long daysBetween(Date smdate, Date bdate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    try {
      smdate = sdf.parse(sdf.format(smdate));
      bdate = sdf.parse(sdf.format(bdate));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    Calendar cal = Calendar.getInstance();
    cal.setTime(smdate);
    long time1 = cal.getTimeInMillis();
    cal.setTime(bdate);
    long time2 = cal.getTimeInMillis();
    long between_days = (time2 - time1) / (1000 * 3600 * 24);

    return Integer.parseInt(String.valueOf(between_days));
  }

}
