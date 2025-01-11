package com.fineias.marketplace.order.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductInfo(
        @NotBlank UUID productId,
        @NotBlank String productName,
        @NotBlank BigDecimal price,
        @NotBlank Integer quantity
) {
}
