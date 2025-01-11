package com.fineias.marketplace.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineias.marketplace.order.dto.OrderRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String ORDER_REQUEST_EXCHANGE = "order-request-exchange";
    private static final String ORDER_REQUEST_ROUTEKEY = "order-request-key";

    public void sendPaymentRequest(OrderRequest requestDTO) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                ORDER_REQUEST_EXCHANGE,
                ORDER_REQUEST_ROUTEKEY,
                objectMapper.writeValueAsString(requestDTO)
        );
    }

}
