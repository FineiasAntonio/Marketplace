package com.fineias.marketplace.user.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cartId;

    private UUID productId;
    private int quantity;

    public CartItem(UUID uuid, int quantity) {
    }

    public CartItem(Cart cartId, UUID productId, int quantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
