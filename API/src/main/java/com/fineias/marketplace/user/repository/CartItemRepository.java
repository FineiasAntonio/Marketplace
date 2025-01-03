package com.fineias.marketplace.user.repository;

import com.fineias.marketplace.user.core.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    @Modifying
    @Query(
            nativeQuery = true,
            value = "UPDATE carts_items SET quantity = :quantity WHERE cart_item_id = :itemCartId"
    )
    void updateItemQuantity(
            @Param("itemCartId") UUID itemCartId,
            @Param("quantity") Integer quantity
    );


}