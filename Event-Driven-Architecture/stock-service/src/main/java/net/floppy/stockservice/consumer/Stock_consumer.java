package net.floppy.stockservice.consumer;

import net.floppy.stockservice.dto.Order;
import net.floppy.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Stock_consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Stock_consumer.class);

    @RabbitListener(queues = "${rabbit.queue.name}")
    public void stock_consumer(OrderEvent orderEvent)
    {
        LOGGER.info(String.format("Stock order received --> %s", orderEvent.toString()));

        //if you want save in database
    }
}
