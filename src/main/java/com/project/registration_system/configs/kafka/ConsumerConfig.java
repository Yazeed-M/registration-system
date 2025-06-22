package com.project.registration_system.configs.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class ConsumerConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("interceptor.classes",
                "com.project.registration_system.configs.kafka.KafkaConsumerInterceptor");

        return new DefaultKafkaConsumerFactory<>(props);
    }
}
