package com.fineias.payment.MarketplacePaymentService.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.resources.payment.Payment;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentResponseProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String CREATE_ORDER_RESPONSE_EXCHANGE = "create-order-response-exchange";
    private static final String CREATE_ORDER_RESPONSE_KEY = "create-order-response-key";

    public void createOrderResponseProducer(Payment payment) {

    }

}
