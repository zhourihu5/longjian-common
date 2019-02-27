package com.longfor.longjian.common.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 日期/时间戳转换工具类
 *
 * @author zkm
 * @date 2019/1/11 14:10
 */
public class DateUtil {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * String转Date
     * 使用默认时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTimeStr
     * @return
     */
    public static Date stringToDate(String dateTimeStr) {
        return stringToDate(dateTimeStr, null);
    }

    /**
     * String转Date
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public static Date stringToDate(String dateTimeStr, String formatStr) {
        if (StringUtils.isEmpty(formatStr)) {
            formatStr = STANDARD_FORMAT;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    /**
     * Date转String
     * 使用默认时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        return dateToString(date, null);
    }

    /**
     * Date转String
     *
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateToString(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isEmpty(formatStr)) {
            formatStr = STANDARD_FORMAT;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    /**
     * String转10位时间戳
     * 使用默认时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateTimeStr
     * @return
     */
    public static Integer stringToTimestamp(String dateTimeStr) {
        return stringToTimestamp(dateTimeStr, null);
    }

    /**
     * String转10位时间戳
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     */
    public static Integer stringToTimestamp(String dateTimeStr, String formatStr) {
        if (StringUtils.isEmpty(formatStr)) {
            formatStr = STANDARD_FORMAT;
        }
        Date date = stringToDate(dateTimeStr, formatStr);
        return new BigDecimal(date.getTime()).divide(new BigDecimal(1000), BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 10位int型的时间戳转换为String
     * 使用默认时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String timestampToString(Integer time) {
        return timestampToString(time, null);
    }

    /**
     * 10位int型的时间戳转换为String
     *
     * @param time
     * @return
     */
    public static String timestampToString(Integer time, String formatStr) {
        if (StringUtils.isEmpty(formatStr)) {
            formatStr = STANDARD_FORMAT;
        }
        long temp = new BigDecimal(time).multiply(new BigDecimal(1000)).longValue();
        Timestamp timestamp = new Timestamp(temp);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        return new DateTime(timestamp).toString(dateTimeFormatter);
    }

    /**
     * 10位int型的时间戳转Date
     *
     * @param time
     * @return
     */
    public static Date timestampToDate(Integer time) {
        long temp = new BigDecimal(time).multiply(new BigDecimal(1000)).longValue();
        return new Date(temp);
    }

    /**
     * Date类型转换为10位int型的时间戳
     *
     * @param time
     * @return
     */
    public static Integer dateToTimestamp(Date time) {
        return new BigDecimal(time.getTime()).divide(new BigDecimal(1000), BigDecimal.ROUND_HALF_UP).intValue();
    }


    /**
     * thisDay的下一天
     *
     * @param thisDay
     * @return
     */
    public static Date nextDate(Date thisDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(thisDay);
        calendar.add(Calendar.DATE,1);
        return calendar.getTime();
    }

    /**
     * 昨天的零点零分零秒
     *
     * @return
     */
    public static Date yesterdayZeroDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    public static boolean isDelete(Date date){
        if(date==null){
            return false;
        }
        Date defaultDate=stringToDate("0001-01-01 00:00:00");
        return !Objects.equals(date,defaultDate);
    }




}
