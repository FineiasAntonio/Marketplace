package com.fineias.marketplace.order.dto;

import com.fineias.marketplace.order.enums.PaymentType;

import java.util.List;
import java.util.UUID;

public record ClientOrderRequest(
    UUID userId,
    List<UUID> cartItemsId,
    PaymentType paymentType
) {
}
