package com.longfor.longjian.common.kafka;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class KafkaProducer {

    @Value("${kafka.kafka_prefix}")
    private String prefix;

    private  String topic_prefix = String.format("%s-", StringUtils.isBlank(prefix) ? "" : prefix);
    private  String event_queue_topic = String.format("%sevents",topic_prefix);

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

        log.info("kafka 推送消息 topic:  {}",topic);
        log.info("kafka 推送消息 data:  {}",JSON.toJSONString(pushData));
        kafkaTemplate.send(topic, JSON.toJSONString(pushData));
    }

    public void produceEvents(String topic, Object data) {
        String eventType = StringUtils.EMPTY;
        String[] topicAndEvent = topic.split(":");
        if (topicAndEvent.length > 1) {
            topic = topicAndEvent[0];
            eventType = topicAndEvent[1];
        }
        topic = String.format("%s-%s", event_queue_topic, topic);
        Map<String, Object> pushData = Maps.newHashMap();
        pushData.put("event_type", eventType);
        pushData.put("data", JSON.toJSONString(data));
        pushData.put("timestamp", System.currentTimeMillis());

        log.info("kafka 推送消息 topic:  {}",eventType);
        log.info("kafka 推送消息 topic:  {}",topic);
        log.info("kafka 推送消息 data:  {}",JSON.toJSONString(pushData));
        kafkaTemplate.send(topic, JSON.toJSONString(pushData));
    }

}
