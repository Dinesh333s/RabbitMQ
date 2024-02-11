package net.floppy.rabbitmqdemo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.name.exchange}")
    private String exchange;
    @Value("${rabbitmq.name.routing}")
    private String routing_key;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendQueue(String message)
    {
        LOGGER.info(String.format("message send -->  %s", message));
        rabbitTemplate.convertAndSend(exchange,routing_key,message);
    }


}
