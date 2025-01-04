package com.fineias.marketplace.user.gateway.adapter;

import com.fineias.marketplace.product.core.model.Product;
import com.fineias.marketplace.product.exception.ProductInsufficientStorageExeception;
import com.fineias.marketplace.product.exception.ProductNotFoundException;
import com.fineias.marketplace.product.gateway.port.ProductPort;
import com.fineias.marketplace.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductAdapter implements ProductPort {

    @Autowired private ProductRepository productRepository;

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public void verifyProductStorage(UUID productId, int requiredQuantity) {
        if (productRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new)
                .getStorage() < requiredQuantity) {
            throw new ProductInsufficientStorageExeception();
        }
    }

    @Override
    public List<Product> getUserSelledProducts(UUID userId) {
        return productRepository.findBySeller(userId);
    }

}
