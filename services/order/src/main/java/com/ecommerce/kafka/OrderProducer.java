package com.ecommerce.kafka;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messsaging.Message;
import org.springframework.messsaging.support.MessageBuilder;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {
    
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Sending order confirmation: {}", orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, Topics.ORDER.label)
                .build();
        kafkaTemplate.send(message);
    }
}
