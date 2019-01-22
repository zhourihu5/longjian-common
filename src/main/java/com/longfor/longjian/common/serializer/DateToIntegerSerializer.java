package com.longfor.longjian.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.longfor.longjian.common.util.DateUtil;

import java.io.IOException;
import java.util.Date;

/**
 * Jackson时间序列化转换
 * 序列化时java.util.Date转换成10位时间戳
 * 精确到秒
 *
 * @author zkm
 * @date 2019/1/19 12:25
 */
public class DateToIntegerSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(DateUtil.dateToTimestamp(date));
    }

}
