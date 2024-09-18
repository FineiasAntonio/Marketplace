package com.fineias.marketplace.user.dto;

import java.util.UUID;

public record ProductToCartDTO(
        UUID productId,
        int quantity
) {
}
