package com.fineias.payment.MarketplacePaymentService.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineias.payment.MarketplacePaymentService.dto.CreateOrderResponse;
import com.fineias.payment.MarketplacePaymentService.dto.UpdateOrderResponse;
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

    private static final String UPDATE_ORDER_RESPONSE_EXCHANGE = "update-order-response-exchange";
    private static final String UPDATE_ORDER_RESPONSE_KEY = "update-order-response-key";

    public void createOrderResponseProduce(CreateOrderResponse createOrderResponse) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                CREATE_ORDER_RESPONSE_EXCHANGE,
                CREATE_ORDER_RESPONSE_KEY,
                objectMapper.writeValueAsString(createOrderResponse)
        );
    }

    public void updateOrderResponseProduce(UpdateOrderResponse updateOrderResponse) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                UPDATE_ORDER_RESPONSE_EXCHANGE,
                UPDATE_ORDER_RESPONSE_KEY,
                objectMapper.writeValueAsString(updateOrderResponse)
        );
    }

}
