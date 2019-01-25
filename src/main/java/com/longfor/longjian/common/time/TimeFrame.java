package com.longfor.longjian.common.time;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 *  LONG, String，Date, 互相转化：https://blog.csdn.net/cheweilol/article/details/73770751
 *
 * @author lipeishuai
 * @date 2019/1/18 19:36
 */

@Data
public class TimeFrame {


   public static final  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private String type;
    private Date day;
    private int year;
    private Date beginOn;
    private Date endOn;
    private Date nextMonth;
    /**
     * 一年中的相关时间段的序号，如一年中的第几周，第几个月
     */
    private int idx;
    private String fmt ="%Y%m%d";

    public TimeFrame(String type, Date day) {

        this.type = type;
        this.day = day;
        trans();
    }

    public TimeFrame(String type, Date day, String fmt) {

        if(StringUtils.isNotEmpty(fmt)){
            this.fmt=fmt;
        }
        this.type = type;
        this.day = day;
        trans();
    }

    /**
     * 根据参数得到起始和结束时间段
     */
    private void trans(){

        if(TimeType.WEEK.getValue().equalsIgnoreCase(this.type)){

            Calendar calendar = Calendar.getInstance();

            //设置星期一为一周开始的第一天
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTimeInMillis(day.getTime());

            /**
             * DAY_OF_WEEK -1 为周几
             * 当前是周几 - 周几数值 + 1 即为周一
             */
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
            if( dayOfWeek == 0){
                dayOfWeek = 7;
            }
            calendar.add(Calendar.DATE, - dayOfWeek+1);
            this.beginOn = calendar.getTime();
            this.year = calendar.get(Calendar.YEAR);
            this.idx = calendar.get(Calendar.WEEK_OF_YEAR);

            //周一+6天， 即为周日
            calendar.add(Calendar.DATE,  6 );
            this.endOn = calendar.getTime();

//            System.out.println("this.day:" + format.format(day)+",周" + dayOfWeek +",weekInYear:" + idx
//                    +",beginOn:" + format.format(beginOn) +",endOn:" + format.format(endOn));



        }else if(TimeType.MONTH.getValue().equalsIgnoreCase(this.type)){

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(day.getTime());
            // 时间改为当前月的第一天
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            this.beginOn = calendar.getTime();
            this.year = calendar.get(Calendar.YEAR);

            //当前月再加1个月即，即为下一个月，再-1天，即为上个月的最后一天
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            this.endOn = calendar.getTime();
            this.idx = calendar.get(Calendar.MONTH) +1;

        }else if(TimeType.QUARTER.getValue().equalsIgnoreCase(this.type)){

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(day.getTime());
            this.year = calendar.get(Calendar.YEAR);
            int currentMonth = calendar.get(Calendar.MONTH) + 1;

            if (currentMonth >= 1 && currentMonth <= 3){
                calendar.set(Calendar.MONTH, 0);
                this.idx = 1;
            }
            else if (currentMonth >= 4 && currentMonth <= 6) {
                calendar.set(Calendar.MONTH, 3);
                this.idx = 2;
            }
            else if (currentMonth >= 7 && currentMonth <= 9) {
                calendar.set(Calendar.MONTH, 4);
                this.idx = 3;
            }
            else if (currentMonth >= 10 && currentMonth <= 12) {
                calendar.set(Calendar.MONTH, 9);
                this.idx = 4;
            }
            calendar.set(Calendar.DATE, 1);
            this.beginOn = calendar.getTime();

            // 求季度最后时间
            calendar.setTimeInMillis(day.getTime());
            currentMonth = calendar.get(Calendar.MONTH) + 1;

            if (currentMonth >= 1 && currentMonth <= 3) {
                calendar.set(Calendar.MONTH, 2);
                calendar.set(Calendar.DATE, 31);
                this.idx = 1;
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                calendar.set(Calendar.MONTH, 5);
                calendar.set(Calendar.DATE, 30);
                this.idx = 2;
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                calendar.set(Calendar.MONTH, 8);
                calendar.set(Calendar.DATE, 30);
                this.idx = 3;
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                calendar.set(Calendar.MONTH, 11);
                calendar.set(Calendar.DATE, 31);
                this.idx = 4;
            }
            this.endOn = calendar.getTime();


        }else if(TimeType.YEAR.getValue().equalsIgnoreCase(this.type)){

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(day.getTime());
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DATE, 1);
            this.beginOn = calendar.getTime();
            this.year = calendar.get(Calendar.YEAR);

            calendar.set(Calendar.MONTH, 11);
            calendar.set(Calendar.DATE, 31);
            this.endOn = calendar.getTime();
        }else{

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(day.getTime());

            this.idx = calendar.get(Calendar.DAY_OF_YEAR);
            this.year = calendar.get(Calendar.YEAR);
            this.beginOn = this.day;
            this.endOn = this.day;
        }

    }


    /**
     * 下一个Frame
     * @return
     */
    public TimeFrame next(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.endOn);
        calendar.add(Calendar.DATE, 1);
        Date nextDay = calendar.getTime();
        return new TimeFrame(this.type, nextDay);
    }

    /**
     * 前一个Frame
     *
     * @return
     */
    public TimeFrame prev(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.beginOn);
        calendar.add(Calendar.DATE, -1);

        Date preDay = calendar.getTime();
        return new TimeFrame(this.type, preDay);
    }


    /**
     *  限制不超过day指定的日期
     *
     * @param isBackward
     * @param limitDay
     */
    public void limit(boolean isBackward, Date limitDay){

        if(limitDay == null){
            limitDay = this.day;
        }

        if(isBackward){
            if (this.day.after(this.beginOn)){
                this.beginOn = limitDay;
            }
        }else{
            if(this.day.before( this.endOn)){
                this.endOn = limitDay;
            }
        }
    }

    @Override
    public String toString() {

        String stringFormat = "<%s: [%d,%d] %s - %s>";
        return String.format(stringFormat, this.type.toUpperCase(), this.year, this.idx, format.format(beginOn),
                format.format(endOn));
    }

}


