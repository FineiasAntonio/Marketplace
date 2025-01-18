package com.fineias.payment.MarketplacePaymentService.dto;

public record UpdateOrderResponse(
        Long orderId,
        String status,
        String details
) {
}
