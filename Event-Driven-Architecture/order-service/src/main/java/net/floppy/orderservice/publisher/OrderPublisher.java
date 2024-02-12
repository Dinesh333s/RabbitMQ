package net.floppy.orderservice.publisher;

import net.floppy.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {
    @Value("${rabbit.exchange.name}")
    private String exchange;
    @Value("${rabbit.routing.name}")
    private String orderRouting_key;

    @Value("${rabbit.erouting.name}")
    private String e_route;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPublisher.class);

    private RabbitTemplate rabbitTemplate;

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(OrderEvent orderEvent)
    {
        LOGGER.info(String.format("Order message sent -->  %s", orderEvent.toString()));
        //For Order QUEUE
        rabbitTemplate.convertAndSend(exchange,orderRouting_key,orderEvent);
        //FOR EMAIL QUEUE
        rabbitTemplate.convertAndSend(exchange,e_route,orderEvent);
    }
}
