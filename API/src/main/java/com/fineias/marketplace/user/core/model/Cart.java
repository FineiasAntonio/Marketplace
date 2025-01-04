package com.fineias.marketplace.user.core.model;

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
    private List<CartItem> productList; // maybe, after i can implement binary search
}
