package com.longfor.longjian.common.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.Resource;
import java.util.*;

/**
 * kafka监听 获取消息内容 推送给对应的用户
 * Created by Wang on 2018/8/21.
 */
public class LhKafkaListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


}
