package com.fineias.payment.MarketplacePaymentService.dto;

public record PaymentInfoResponse(
        Long id,
        String qr_code
) {
}
