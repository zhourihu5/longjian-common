package com.longfor.longjian.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期/时间戳转换工具类
 *
 * @author zkm
 * @date 2019/1/11 14:10
 */
@Slf4j
public class DateUtil {

    private static final SimpleDateFormat FULL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * String(yyyy-MM-dd HH:mm:ss)转Date
     *
     * @param time
     * @return
     */
    public static Date stringToDate(String time) {
        Date date = null;
        try {
            date = FULL_DATE_FORMAT.parse(time);
        } catch (ParseException e) {
            log.info("时间转换异常", e);
        }
        return date;
    }

    /**
     * Date转String(yyyy-MM-dd HH:mm:ss)
     *
     * @param time
     * @return
     */
    public static String dateToString(Date time) {
        return FULL_DATE_FORMAT.format(time);
    }

    /**
     * String(yyyy-MM-dd HH:mm:ss)转10位时间戳
     *
     * @param time
     * @return
     */
    public static Integer stringToTimestamp(String time) {
        return new BigDecimal(Timestamp.valueOf(time).getTime()).divide(new BigDecimal(1000), BigDecimal.ROUND_HALF_UP).intValue();
    }

    /**
     * 10位int型的时间戳转换为String(yyyy-MM-dd HH:mm:ss)
     *
     * @param time
     * @return
     */
    public static String timestampToString(Integer time) {
        long temp = new BigDecimal(time).multiply(new BigDecimal(1000)).longValue();
        Timestamp timestamp = new Timestamp(temp);
        return FULL_DATE_FORMAT.format(timestamp);
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

}
