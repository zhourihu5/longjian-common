package com.longfor.longjian.common.kafka;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class KafkaProducer {

    @Value("kafka.kafka_prefix")
    private String prefix;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void produce(String topic, Object data) {
        String[] topicAndEvent = topic.split(":");
        if (topicAndEvent.length <= 1) return;
        topic = topicAndEvent[0];
        String eventType = topicAndEvent[1];
        if (StringUtils.isNotBlank(prefix)) {
            topic = String.format("%s-%s", prefix, topic);
        }

        Map<String, Object> pushData = Maps.newHashMap();
        pushData.put("event_type", eventType);
        pushData.put("data", JSON.toJSONString(data));
        pushData.put("timestamp", new Date().getTime());

        kafkaTemplate.send(topic, JSON.toJSONString(JSON.toJSONString(pushData)));

    }
}
