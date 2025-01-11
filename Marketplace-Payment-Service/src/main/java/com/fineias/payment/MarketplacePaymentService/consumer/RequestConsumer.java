package com.fineias.payment.MarketplacePaymentService.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineias.payment.MarketplacePaymentService.dto.OrderRequest;
import com.fineias.payment.MarketplacePaymentService.service.PaymentService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RequestConsumer {

    @Autowired
    private PaymentService paymentService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = {"order-request-queue"})
    public void consumeRequest(@Payload String paymentRequest) {
        try {
            OrderRequest orderRequest = objectMapper.readValue(paymentRequest, OrderRequest.class);
            paymentService.generatePaymentOrder(orderRequest);
        } catch (JsonProcessingException | MPException | MPApiException e) {
            System.out.println(e.getMessage());
        }
    }

}
