package com.fineias.marketplace.user.core.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UserSelledProductsDTO(
        UUID productId,
        String productName,
        BigDecimal price,
        boolean isActivated
) {
}
