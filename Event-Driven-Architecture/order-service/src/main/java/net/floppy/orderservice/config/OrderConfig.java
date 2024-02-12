package net.floppy.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Value("${rabbit.queue.name}")
    private String queue;

    @Value("${rabbit.exchange.name}")
    private String exchange;

    @Value("${rabbit.routing.name}")
    private String routing;

    @Value("${rabbit.erouting.name}")
    private String eroute;
    @Value("${rabbit.equeue.name}")
    private String equeue;

    //create queue
    @Bean
    public Queue order_queue()
    {
        return new Queue(queue);
    }
    //create exchange
    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(exchange);
    }
    //bind queue and exchange with routing key
    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(order_queue()).to(exchange()).with(routing);
    }

    /*EMail QUEUE*/
    //create queue
    @Bean
    public Queue email_queue()
    {
        return new Queue(equeue);
    }
    //bind queue and exchange with routing key
    @Bean
    public Binding email_binding()
    {
        return BindingBuilder.bind(email_queue()).to(exchange()).with(eroute);
    }



    //message json converter
    @Bean
    public MessageConverter messageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }
    //rabbit template to call message converter func
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
