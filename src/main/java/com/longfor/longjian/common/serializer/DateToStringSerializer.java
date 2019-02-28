package com.longfor.longjian.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.longfor.longjian.common.util.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author zkm
 * @date 2019/2/28 16:22
 */
public class DateToStringSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String dateStr = StringUtils.EMPTY;
        if (date != null) {
            dateStr = DateUtil.dateToString(date);
            dateStr = dateStr.startsWith("0000") ? StringUtils.EMPTY : Long.valueOf(date.getTime()).toString();
        }
        jsonGenerator.writeString(dateStr);
    }
}
