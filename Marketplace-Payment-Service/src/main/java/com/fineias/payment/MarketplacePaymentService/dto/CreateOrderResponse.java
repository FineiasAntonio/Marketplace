package com.fineias.payment.MarketplacePaymentService.dto;

import com.fineias.payment.MarketplacePaymentService.enums.PaymentType;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderResponse(
        UUID userId,
        UUID productId,
        Integer quantity,
        BigDecimal totalAmount,
        PaymentType paymentType,
        String qrCode
        ) {}
