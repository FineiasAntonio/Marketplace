package com.fineias.payment.MarketplacePaymentService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClientConfig(WebClient.Builder builder) {
        return builder.baseUrl("https://api.mercadopago.com/v1").build();
    }

}
