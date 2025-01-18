package com.fineias.payment.MarketplacePaymentService.service;

import com.fineias.payment.MarketplacePaymentService.dto.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MercadoPagoAdapter {

    @Autowired
    private WebClient webClient;

    @Value("${mercadopago.acesstoken}")
    private String MERCADOPAGO_ACESS_TOKEN;

    Pattern pattern = Pattern.compile("\"id\":\"(\\d+)\"");

    public StatusResponse returnPaymentOrderById(String webhookBodyContent) {
        String orderId;
        Matcher idMatcher = pattern.matcher(webhookBodyContent);

        if (idMatcher.find()) {
            orderId = idMatcher.group(1);
        } else {
            throw new RuntimeException("id not found");
        }

        StatusResponse statusResponse = webClient.get()
                .uri("/payments/" + orderId)
                .header("Authorization", "Bearer " + MERCADOPAGO_ACESS_TOKEN)
                .retrieve()
                .toEntity(StatusResponse.class)
                .block()
                .getBody();

        return new StatusResponse(orderId, statusResponse.status(), statusResponse.status_detail());
    }

}
