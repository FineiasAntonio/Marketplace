package com.fineias.marketplace.user.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @OneToMany(mappedBy = "cartId")
    private List<CartItem> productList;

    public void addItems(CartItem cartItem) {
        this.productList.add(cartItem);
    }

    // This isn't best option, but will work
    public void removeItems(UUID productId) {
        Optional<CartItem> cartItemFound = this.productList.stream().filter(item -> item.getProductId() == productId).findAny();

        if (cartItemFound.isEmpty()) {
            throw new RuntimeException("Product not found in cart");
        }

        this.productList.remove(cartItemFound.get());

    }

}
