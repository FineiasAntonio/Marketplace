package com.fineias.marketplace.order.dto;

import com.fineias.marketplace.order.enums.PaymentType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderResponse(
        @NotBlank Long orderId,
        @NotBlank UUID userId,
        @NotBlank List<ProductInfo> productInfoList,

        @NotBlank
        @DecimalMin(value = "0.01")
        BigDecimal totalAmount,

        @NotBlank PaymentType paymentType,
        @NotBlank String qrCode
        ) {}
