package com.fineias.marketplace.product.dto;

public record ProductSummaryResponseDTO(
        String productName,
        String category,
        Integer price
) {
}
