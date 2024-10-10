package com.fineias.marketplace.user.service;

import com.fineias.marketplace.user.dto.ProductToCartDTO;
import com.fineias.marketplace.user.model.Cart;
import com.fineias.marketplace.user.model.CartItem;
import com.fineias.marketplace.user.model.User;
import com.fineias.marketplace.user.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public void putItemsCart(ProductToCartDTO productToCartDTO) {

        Cart userCart;

        User authenticatedUser;
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            authenticatedUser = (User) principal;
            userCart = cartRepository.findById(authenticatedUser.getCartId()).get();
        } else {
            throw new RuntimeException("Principal isn't a instance of User");
        }

        userCart.addItems(new CartItem(productToCartDTO.productId(), productToCartDTO.quantity()));

        System.out.println(userCart);

        cartRepository.save(userCart);

    }

}
