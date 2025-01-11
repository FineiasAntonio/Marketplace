package com.fineias.marketplace.order.dto;

import com.fineias.marketplace.order.enums.OrderStatus;

import java.util.UUID;

public record OrderUpdateRequestDTO(
        UUID orderId,
        OrderStatus status
) {
}
