package com.fineias.marketplace.user.core.dto;

import java.util.UUID;

public record ProductCartDetailsDTO(
        UUID productId,
        int quantity
) {
}
