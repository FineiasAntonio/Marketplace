package com.fineias.payment.MarketplacePaymentService.dto;

import com.fineias.payment.MarketplacePaymentService.enums.PaymentType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderRequest(
        UUID userId,
        String userName,
        String userEmail,

        List<ProductInfo> productInfoList,

        BigDecimal totalAmount,

        PaymentType paymentType
) {}
