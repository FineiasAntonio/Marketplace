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

    @Transient
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @Transient
    private List<CartItem> productList = new LinkedList<>();

    @Column(columnDefinition = "jsonb", name = "product_list")
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private String jsonProductList = "";

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

    @PostLoad
    private void instantiateProductList() {
        try {
            System.out.println("foi instanciado");
            this.productList = objectMapper.readValue(this.jsonProductList, objectMapper.getTypeFactory().constructCollectionType(List.class, CartItem.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing attribute");
        }
    }

    @PrePersist
    @PreUpdate
    private void writeProductList() {
        try {
            System.out.println("foi escrito");
            this.jsonProductList = objectMapper.writeValueAsString(this.productList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while parsing attribute");
        }
    }

}
