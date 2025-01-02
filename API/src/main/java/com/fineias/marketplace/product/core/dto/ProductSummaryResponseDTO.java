package com.fineias.marketplace.product.core.dto;

public record ProductSummaryResponseDTO(
        String productName,
        String category,
        Float price
) {
}
