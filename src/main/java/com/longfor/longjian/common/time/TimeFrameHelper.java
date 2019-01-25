package com.longfor.longjian.common.time;

import com.google.common.collect.Lists;
import com.longfor.longjian.common.exception.LjBaseRuntimeException;
import com.longfor.longjian.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 *  时间段生成器  指定起止时间（或能转话为起止时间），生成指定类型的连续时间段
 *
 *   初始化生成器

 需要能明确开始和结束日期
 即至少保证以下几种搭配：
 从指定日期开始 typ + max_count + begin_on
 至指定日期结束 typ + max_count + [end_on]
 指定日期范围内 typ + begin_on + end_on
 若limit_scope为True，则生成的时间段会修改起止日期以保证不超过限制
 特别的，若未指定typ，则begin_on和end_on必填，max_count和limit_scope无效

 初始化完成后，frames里保存生成好的时间段列表
 指定typ时，frames里保存的时间段的typ都为指定typ
 未指定typ时，frames里保存从begin_on到end_on需要的最少时间段列表
 *
 * @author lipeishuai
 * @date 2019/1/18 17:31
 */
public class TimeFrameHelper {

    /**
     *  使用方式，x
     *  （1）type 非空 + maxCount >1 + beginDate非空 + endDate为空 => 以beginDate起始， 向明天数maxCount个周期
     *   见下面样例：testEndDateIsNull()
     *  （2）type 非空 + maxCount>1 + beginDate非空 + endDate非空 =>  以endDate起始， 向过去数maxCount个周期
     *  见下面样例：testEndDateNotNull()
     *  （3）type 非空 + maxCount=0 + beginDate非空 + endDate非空, 见样例：testMaxCountIs0()
     *  （4）type为空，maxCount、beginDate和endDate非空：见样例：testNoType()
     *
     *
     * @param type
     * @param maxCount
     * @param beginDate
     * @param endDate
     * @param limitScope
     * @return
     */
    public static List<TimeFrame> produceFrames(String type, int maxCount, Date beginDate, Date endDate, boolean limitScope){

        //type非空，且为(DAY, WEEK, MONTH, QUARTER, YEAR):
        if(StringUtils.isNoneEmpty(type)) {
            type = type.toLowerCase();
            if( !TimeType.typeList.contains(type)){
                throw new LjBaseRuntimeException(430,"type not one of (DAY, WEEK, MONTH, QUARTER, YEAR)");
            }
        }

        if (beginDate == null && endDate == null){
            if(StringUtils.isEmpty(type)){
                throw new LjBaseRuntimeException(430,"beginOn and endOn can not both null when Type is null");
            }
            endDate = new Date();
        }

        if (beginDate!=null && endDate!=null ){
            if(beginDate.after(endDate)){
                Date tmp = beginDate;
                beginDate = endDate;
                endDate = tmp;
            }
        }else if(maxCount <= 0){
            maxCount =1;
        }

        List<TimeFrame> frames = null;
       if(StringUtils.isNoneEmpty(type)){
           frames = produceFramesWithType(type, maxCount, beginDate, endDate, limitScope);
       }else{
           frames= produceFramesNonType(maxCount,beginDate,endDate,limitScope);
       }



        return frames;

    }


    /**
     * 有Type时
     *
     * @param type
     * @param maxCount
     * @param beginDate
     * @param endDate
     * @param limitScope
     * @return
     */
    private static List<TimeFrame> produceFramesWithType(String type, int maxCount, Date beginDate, Date endDate, boolean limitScope){

        boolean isBackward;
        if(endDate !=null){
            isBackward = true;
        }else{
            isBackward = false;
        }

        Date tmpBegin = beginDate;
        Date tmpEnd = endDate;

        if(isBackward){
            tmpBegin = endDate;
            tmpEnd = beginDate;
        }

        TimeFrame frame = new TimeFrame(type, tmpBegin);

        if(limitScope){
            frame.limit(!isBackward, null);
        }

        List<TimeFrame> result = Lists.newArrayList(frame);

        while(true){

            if (tmpEnd !=null){
                if(isBackward){
                    if( !frame.getBeginOn().after(tmpEnd) ){
                        if(limitScope){
                            frame.limit(isBackward, tmpEnd);
                        }
                        break;
                    }
                }else{
                    if( !frame.getEndOn().before(tmpEnd) ){
                        if(limitScope){
                            frame.limit(isBackward, tmpEnd);
                        }
                        break;
                    }
                }
            }

            if (maxCount > 0){
                if(result.size() >= maxCount){
                    break;
                }
            }

            if(isBackward){
                frame = frame.prev();
            }else{
                frame = frame.next();
            }
            result.add(frame);
        }

        if(isBackward){
            Collections.reverse(result);
        }

        return result;
    }


    /**
     * 无type时
     *
     * @param maxCount
     * @param beginDate
     * @param endDate
     * @param limitScope
     * @return
     */
    private static List<TimeFrame> produceFramesNonType(int maxCount, Date beginDate, Date endDate, boolean limitScope){

        Date tmpBegin = beginDate;
        Date tmpEnd = endDate;
        List<TimeFrame> result = Lists.newArrayList();

        while (!tmpBegin.after(tmpEnd)){

            for(String type : TimeType.typeList){

                TimeFrame frame = new TimeFrame(type, tmpBegin);

                if(TimeType.WEEK.getValue().equalsIgnoreCase(type)
                        && frame.getBeginOn().getMonth()!= frame.getEndOn().getMonth()){
                    continue;
                }

                if(frame.getBeginOn().equals(tmpBegin) && !frame.getEndOn().after(tmpEnd)){
                    result.add(frame);
                    tmpBegin = DateUtil.nextDate(frame.getEndOn());
//                    System.out.println("nextDate -tmpBegin :" + TimeFrame.format.format(tmpBegin));
                }
            }
        }

        return result;
    }

   public static void main(String []args) {

       testNoType();
       testEndDateIsNull();
       testEndDateNotNull();
       testMaxCountIs0();
    }


    /**
     * testNoType
     */
    private static void testNoType() {

        System.out.println("------- testNoType Begin: forward-------");

        Date beginDate = DateUtil.stringToDate("2018-10-22 00:12:40");
        Date endDate1 = DateUtil.stringToDate("2019-02-24 00:12:40");

        List<TimeFrame> frames = produceFrames(null, 5, beginDate, endDate1, true);

        for (TimeFrame frame : frames) {
            System.out.println(frame);
        }
        System.out.println("------- testNoType End -------");

    }


    /**
     * testEndDateIsNull
     */
    private static void testEndDateIsNull() {

        System.out.println("------- testEndDateIsNull Begin: forward-------");

        Date beginDate = DateUtil.stringToDate("2018-10-22 00:12:40");
        Date endDate1 = DateUtil.stringToDate("2019-01-24 00:12:40");

        List<TimeFrame> frames = produceFrames(TimeType.WEEK.getValue(), 5, beginDate, null, true);

        for (TimeFrame frame : frames) {
            System.out.println(frame);
        }
        System.out.println("------- testEndDateIsNull End -------");

    }

    /**
     * testEndDateNotNull
     */
    private static void testEndDateNotNull() {

        System.out.println("------- testEndDateNotNull: backward -------");

        Date beginDate = DateUtil.stringToDate("2018-10-22 00:12:40");
        Date endDate1 = DateUtil.stringToDate("2019-01-24 00:12:40");

        List<TimeFrame> frames = produceFrames(TimeType.WEEK.getValue(), 5, beginDate, endDate1, true);

        for (TimeFrame frame : frames) {
            System.out.println(frame);
        }

        System.out.println("------- testEndDateNotNull End -------");
    }

    /**
     * testMaxCountIs0
     */
    private static void testMaxCountIs0() {

        System.out.println("------- testMaxCountIs0 Begin -------");
        Date beginDate = DateUtil.stringToDate("2018-10-22 00:12:40");
        Date endDate1 = DateUtil.stringToDate("2019-01-24 00:12:40");

        List<TimeFrame> frames = produceFrames(TimeType.WEEK.getValue(), 0, beginDate, endDate1, true);

        for (TimeFrame frame : frames) {
            System.out.println(frame);
        }
        System.out.println("------- testMaxCountIs0 End -------");
    }

    /**
     * all
     */
   private static void all(){

        Date beginDate= DateUtil.stringToDate("2018-10-22 00:12:40");
        Date endDate1= DateUtil.stringToDate("2019-12-20 00:12:40");

        List<TimeFrame> frames =  produceFrames(TimeType.WEEK.getValue(), 10, beginDate, endDate1, true );
        for(TimeFrame frame : frames){
            System.out.println(frame);
        }

        frames =  produceFrames(TimeType.MONTH.getValue(), 10, beginDate, endDate1, true );
        for(TimeFrame frame : frames){
            System.out.println(frame);
        }

        frames =  produceFrames(TimeType.YEAR.getValue(), 10, beginDate, endDate1, true );
        for(TimeFrame frame : frames){
            System.out.println(frame);
        }

        frames =  produceFrames(TimeType.QUARTER.getValue(), 10, beginDate, endDate1, true );
        for(TimeFrame frame : frames){
            System.out.println(frame);
        }

        frames =  produceFrames(TimeType.DAY.getValue(), 10, beginDate, endDate1, true );
        for(TimeFrame frame : frames){
            System.out.println(frame);
        }
    }
}
