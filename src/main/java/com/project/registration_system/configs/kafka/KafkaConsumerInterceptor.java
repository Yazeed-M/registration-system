package com.project.registration_system.configs.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.Map;

import com.project.registration_system.components.user.dtos.UserNotificationsDto;

public class KafkaConsumerInterceptor implements ConsumerInterceptor<String,String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        // take the first partition since it is only one record
        TopicPartition topicPartition = records.partitions().iterator().next();// gets the first partition
        List<ConsumerRecord<String, String>> recordList = records.records(topicPartition); // the single record
        try{
            UserNotificationsDto dto = objectMapper.readValue(recordList.get(0).value(), UserNotificationsDto.class);
            if(dto.isValid()){
                return records;
            }
            else{
                System.err.println("error validating the message");
            }
        }catch (Exception e){
            System.err.println("Invalid payload: " + e.getMessage());
        }


        return new ConsumerRecords<>(Map.of());
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
