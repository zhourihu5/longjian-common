package com.longfor.longjian.common.kafka;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Kafka生产者
 *
 * @author zhouxingjia
 */
@Component
public class KafkaProducer {

    @Value("kafka.kafka_prefix")
    private String prefix;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void produce(String topic, Object data) {
        String eventType = StringUtils.EMPTY;
        String[] topicAndEvent = topic.split(":");
        if (topicAndEvent.length > 1) {
            topic = topicAndEvent[0];
            eventType = topicAndEvent[1];
        }
        if (StringUtils.isNotBlank(prefix)) {
            topic = String.format("%s-%s", prefix, topic);
        }
        Map<String, Object> pushData = Maps.newHashMap();
        pushData.put("event_type", eventType);
        pushData.put("data", JSON.toJSONString(data));
        pushData.put("timestamp", System.currentTimeMillis());
        kafkaTemplate.send(topic, JSON.toJSONString(pushData));
    }

}
