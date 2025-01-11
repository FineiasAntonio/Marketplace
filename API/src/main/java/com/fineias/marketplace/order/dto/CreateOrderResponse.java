package com.fineias.marketplace.order.dto;

import com.fineias.marketplace.order.enums.PaymentType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderResponse(
        @NotBlank UUID userId,
        @NotBlank UUID productId,
        @NotBlank Integer quantity,

        @NotBlank
        @DecimalMin(value = "0.01")
        BigDecimal totalAmount,

        @NotBlank PaymentType paymentType,
        @NotNull UUID cardId,

        @NotNull
        @Min(value = 100)
        @Max(value = 999)
        Integer cvc,

        String qrCode
        ) {}
