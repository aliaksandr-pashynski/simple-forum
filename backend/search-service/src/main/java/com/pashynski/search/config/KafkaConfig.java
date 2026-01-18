package com.pashynski.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ContainerPostProcessor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;

@Configuration
public class KafkaConfig {

    @Bean
    public ContainerPostProcessor<String, String, AbstractMessageListenerContainer<String, String>> listenerPostProcessor(KafkaTemplate<String, Object> kafkaTemplate) {
        return container -> {
            DeadLetterPublishingRecoverer deadLetterPublishingRecoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);
            DefaultErrorHandler errorHandler = new DefaultErrorHandler(deadLetterPublishingRecoverer);
            container.setCommonErrorHandler(errorHandler);
        };
    }
}
