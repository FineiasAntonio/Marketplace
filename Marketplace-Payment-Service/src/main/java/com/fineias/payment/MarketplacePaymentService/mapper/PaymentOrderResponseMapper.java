package com.fineias.payment.MarketplacePaymentService.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineias.payment.MarketplacePaymentService.dto.*;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentOrderResponseMapper {

    ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public CreateOrderResponse toCreateResponse(Payment payment, OrderRequest orderRequest) throws JsonProcessingException {
        PaymentInfoResponse paymentInfoResponse = objectMapper.readValue(payment.getResponse().getContent(), PaymentInfoResponse.class);
        return new CreateOrderResponse(
                paymentInfoResponse.id(),
                orderRequest.userId(),
                orderRequest.productInfoList(),
                orderRequest.totalAmount(),
                orderRequest.paymentType(),
                paymentInfoResponse.qr_code()
        );
    }

    public UpdateOrderResponse toUpdateResponse(StatusResponse webhookStatusResponse) {
        return new UpdateOrderResponse(
                Long.parseLong(webhookStatusResponse.orderId()),
                webhookStatusResponse.status(),
                webhookStatusResponse.status_detail()
        );
    }

}
