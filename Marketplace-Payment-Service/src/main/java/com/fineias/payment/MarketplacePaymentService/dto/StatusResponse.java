package com.fineias.payment.MarketplacePaymentService.dto;

public record StatusResponse(
        String orderId,
        String status,
        String status_detail
) {
}
