package com.fineias.marketplace.user.service;

import com.fineias.marketplace.product.gateway.ProductGateway;
import com.fineias.marketplace.user.core.dto.ProductCartDetailsDTO;
import com.fineias.marketplace.user.core.model.Cart;
import com.fineias.marketplace.user.core.model.CartItem;
import com.fineias.marketplace.user.exception.CartNotFoundException;
import com.fineias.marketplace.user.exception.ProductNotFoundInCartException;
import com.fineias.marketplace.user.repository.CartItemRepository;
import com.fineias.marketplace.user.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Log4j2
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductGateway productGateway;

    @Transactional
    public void putItemsCart(ProductCartDetailsDTO productCartDetailsDTO) {

        Cart userCart = cartRepository.findById(userService.getAuthenticatedUser().getCart().getCartId())
                .orElseThrow(() -> new RuntimeException("couldn't possible to get cart with this ID"));

        productGateway.verifyProductStorage(productCartDetailsDTO.productId(), productCartDetailsDTO.quantity());

        cartItemRepository.save(new CartItem(userCart, productCartDetailsDTO.productId(), productCartDetailsDTO.quantity()));
    }

    @Transactional
    public void removeCartItems(UUID productSequenceId) {

        Cart userCart = cartRepository.findById(userService.getAuthenticatedUser().getCart().getCartId())
                .orElseThrow(CartNotFoundException::new);

        cartItemRepository.deleteById(productSequenceId);
    }

    @Transactional
    public void updateItemQuantity(UUID itemCartId, Integer quantity) {

        Cart userCart = cartRepository.findById(userService.getAuthenticatedUser().getCart().getCartId())
                .orElseThrow(CartNotFoundException::new);

        if (userCart.getProductList().stream().noneMatch(item -> item.getCartItemId().equals(itemCartId))) {
            throw new ProductNotFoundInCartException();
        }

        cartItemRepository.updateItemQuantity(itemCartId, quantity);

    }
}
