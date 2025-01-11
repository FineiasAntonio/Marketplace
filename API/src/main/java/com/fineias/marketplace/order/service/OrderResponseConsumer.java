package com.fineias.marketplace.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineias.marketplace.order.dto.CreateOrderResponse;
import com.fineias.marketplace.order.dto.OrderUpdateRequestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseConsumer {

    @Autowired
    private OrderService orderService;

    private static final String CREATE_ORDER_RESPONSE_QUEUE = "create-order-response-queue";
    private static final String UPDATE_ORDER_RESPONSE_QUEUE = "update-order-response-queue";

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = {CREATE_ORDER_RESPONSE_QUEUE})
    public void listenMessage(@Payload String payload) {
        try {
            CreateOrderResponse createOrderResponse = objectMapper.readValue(payload, CreateOrderResponse.class);
            orderService.createOrderRequest(createOrderResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
