package com.fineias.marketplace.user.endpoint;

import com.fineias.marketplace.user.core.dto.ProductCartDetailsDTO;
import com.fineias.marketplace.user.service.CartService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/cart")
public class CartController {

    @Autowired private CartService cartService;

    @PostMapping
    public ResponseEntity<Void> putCartItem(@RequestBody ProductCartDetailsDTO productCartDetails) {
        cartService.putItemsCart(productCartDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeCartItem(@RequestParam(name = "productCartId") UUID productCartId) {
        cartService.removeCartItems(productCartId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateCartItemQuantity(
            @RequestParam(name = "productCartId") UUID productCartId,
            @RequestParam(name = "quantity") Integer quantity
    ) {
        cartService.updateItemQuantity(productCartId, quantity);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
