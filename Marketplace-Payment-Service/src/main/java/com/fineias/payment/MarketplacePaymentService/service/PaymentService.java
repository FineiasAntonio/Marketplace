package com.fineias.payment.MarketplacePaymentService.service;

import com.fineias.payment.MarketplacePaymentService.dto.OrderRequest;
import com.fineias.payment.MarketplacePaymentService.mapper.PaymentOrderResponseMapper;
import com.fineias.payment.MarketplacePaymentService.producer.PaymentResponseProducer;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentResponseProducer paymentResponseProducer;
    @Autowired
    private PaymentOrderResponseMapper paymentMapper;

    @Value("${mercadopago.acesstoken}")
    private String MERCADOPAGO_ACESS_TOKEN;

    public void generatePaymentOrder(OrderRequest orderRequest) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(MERCADOPAGO_ACESS_TOKEN);

        Map<String, String> customHeaders = new HashMap<>();
        customHeaders.put("x-idempotency-key", String.valueOf(UUID.randomUUID()));

        MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

        PaymentClient client = new PaymentClient();

        StringBuilder description = new StringBuilder();
        orderRequest.productInfoList().forEach(product -> {
            description.append("%s - %s (%d)\n".formatted(product.productName(), product.price().toString(), product.quantity()));
        });

        PaymentCreateRequest paymentCreateRequest =
                PaymentCreateRequest.builder()
                        .transactionAmount(orderRequest.totalAmount())
                        .description(description.toString())
                        .paymentMethodId("pix")
                        .dateOfExpiration(OffsetDateTime.now().plusMinutes(30))
                        .payer(
                                PaymentPayerRequest.builder()
                                        .firstName(orderRequest.userName())
                                        .email(orderRequest.userEmail())
                                        .build())
                        .build();

        Payment paymentResponse = client.create(paymentCreateRequest, requestOptions);
        try {
            paymentMapper.toCreateResponse(paymentResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
