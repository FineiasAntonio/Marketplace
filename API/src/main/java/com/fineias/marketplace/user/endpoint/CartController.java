package com.fineias.marketplace.user.endpoint;

import com.fineias.marketplace.user.core.dto.ProductCartDetailsDTO;
import com.fineias.marketplace.user.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Void> putProductInCart(@RequestBody ProductCartDetailsDTO productCartDetails) {

        cartService.putItemsCart(productCartDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
