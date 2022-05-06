package com.tjoner.rabbitmqtraceidmessagepostprocessor.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoConsumer {

    @RabbitListener(queues = "demo.queue", containerFactory = "loggingSimpleRabbitListenerContainerFactory")
// use container factory with LoggingMessagePostProcessor
    public void onEvent(String message) {
        log.info("Event consumed by RabbitListener");
    }
}
