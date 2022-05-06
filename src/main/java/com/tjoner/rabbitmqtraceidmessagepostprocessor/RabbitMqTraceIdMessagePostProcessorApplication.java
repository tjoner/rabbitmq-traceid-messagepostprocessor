package com.tjoner.rabbitmqtraceidmessagepostprocessor;

import com.tjoner.rabbitmqtraceidmessagepostprocessor.producer.DemoProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RabbitMqTraceIdMessagePostProcessorApplication implements CommandLineRunner {

    @Autowired
    private DemoProducer demoProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqTraceIdMessagePostProcessorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        demoProducer.publish();
    }
}
