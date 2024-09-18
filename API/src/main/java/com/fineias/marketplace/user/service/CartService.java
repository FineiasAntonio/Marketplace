package com.fineias.marketplace.user.service;

import com.fineias.marketplace.user.dto.ProductToCartDTO;
import com.fineias.marketplace.user.model.Cart;
import com.fineias.marketplace.user.model.CartItem;
import com.fineias.marketplace.user.model.User;
import com.fineias.marketplace.user.repository.CartRepository;
import com.fineias.marketplace.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class CartService {

    private final UserRepository userRepository;
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

//        Cart savedCart = authenticatedUser.getCart().addItems(new CartItem(productToCartDTO.productId(), productToCartDTO.quantity()));
        cartRepository.save(userCart);
//        System.out.println(userRepository.save(authenticatedUser));

    }

}
