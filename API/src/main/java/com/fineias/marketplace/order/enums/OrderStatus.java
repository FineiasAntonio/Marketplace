package com.fineias.marketplace.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    AWAITING ("pending"),
    CANCELLED ("cancelled"),
    APPROVED ("approved");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.getStatus().equalsIgnoreCase(status)) {
                return orderStatus;
            }
        }
        throw new IllegalArgumentException("Invalid status");
    }

}
