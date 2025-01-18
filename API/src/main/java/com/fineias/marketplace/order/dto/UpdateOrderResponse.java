package com.fineias.marketplace.order.dto;

public record UpdateOrderResponse(
        Long orderId,
        String status,
        String details
) {
}
