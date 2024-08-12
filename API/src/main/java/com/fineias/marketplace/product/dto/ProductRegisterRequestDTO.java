package com.fineias.marketplace.product.dto;

public record ProductRegisterRequestDTO(
        String productName,
        String category,
        String description,
        Integer price,
        Integer storage
) {
}
