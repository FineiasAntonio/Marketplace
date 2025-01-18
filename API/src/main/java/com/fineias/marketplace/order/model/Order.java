package com.fineias.marketplace.order.model;

import com.fineias.marketplace.order.enums.OrderStatus;
import com.fineias.marketplace.order.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    private Long orderId;

    private UUID userId;
    @Enumerated(value = EnumType.STRING)
    private PaymentType paymentType;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private String details;

    private String qrCode;

}
