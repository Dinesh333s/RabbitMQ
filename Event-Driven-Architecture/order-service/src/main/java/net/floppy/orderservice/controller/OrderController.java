package net.floppy.orderservice.controller;

import net.floppy.orderservice.dto.Order;
import net.floppy.orderservice.dto.OrderEvent;
import net.floppy.orderservice.publisher.OrderPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class OrderController {
    private OrderPublisher orderPublisher;

    public OrderController(OrderPublisher orderPublisher) {
        this.orderPublisher = orderPublisher;
    }

    @PostMapping("orders")
    public ResponseEntity<String> orders(@RequestBody Order order)
    {
        order.setOrderID(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setStatus("PENDING..");
        orderEvent.setMessage("order is in pending state...");
        orderPublisher.sendJsonMessage(orderEvent);
        return ResponseEntity.ok("Order sent to RabbitMQ...");
    }
}
