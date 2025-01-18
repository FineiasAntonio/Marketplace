package com.fineias.payment.MarketplacePaymentService.controller;

import com.fineias.payment.MarketplacePaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentUpdateWebhook {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/teste")
    public void receiveWebhook(@RequestBody String body) {
        paymentService.updatePaymentOrder(body);
    }

}
