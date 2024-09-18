package com.fineias.marketplace.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CartItem {

    private UUID productId;
    private int quantity;

}
