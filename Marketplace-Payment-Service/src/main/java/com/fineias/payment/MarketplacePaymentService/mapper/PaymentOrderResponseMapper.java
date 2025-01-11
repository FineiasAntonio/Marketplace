package com.fineias.payment.MarketplacePaymentService.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fineias.payment.MarketplacePaymentService.dto.CreateOrderResponse;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class PaymentOrderResponseMapper {

    ObjectMapper objectMapper = new ObjectMapper();

    public CreateOrderResponse toCreateResponse(Payment payment) throws IOException {
        File file = new File("response.txt");
        objectMapper.writeValue(file, payment.getResponse().getContent());
        return null;
    }

}
