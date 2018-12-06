package com.projects.callcenter.services;

import com.projects.callcenter.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CallCenterService {

    private static final Logger logger = LoggerFactory.getLogger(CallCenterService.class);

    private static final String MESSAGE = "Hello world!";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void call() {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_NAME, RabbitMqConfig.ROUTING_KEY, MESSAGE);
    }
}
