package com.fineias.marketplace.product.core.dto;

public record ProductUpdateRequestDTO(
        String productName,
        String category,
        String description,
        Float price,
        Integer storage
) {
}
