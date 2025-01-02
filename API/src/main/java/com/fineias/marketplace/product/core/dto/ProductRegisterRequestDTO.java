package com.fineias.marketplace.product.core.dto;

public record ProductRegisterRequestDTO(
        String productName,
        String category,
        String description,
        Float price,
        Integer storage,
        Boolean activated
) {
}
