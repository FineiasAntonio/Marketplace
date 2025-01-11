package com.fineias.payment.MarketplacePaymentService.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductInfo(
        UUID productId,
        String productName,
        BigDecimal price,
        Integer quantity
) {
}
