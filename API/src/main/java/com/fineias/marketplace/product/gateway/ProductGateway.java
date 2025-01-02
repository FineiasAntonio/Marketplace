package com.fineias.marketplace.product.gateway;

import com.fineias.marketplace.product.exception.ProductInsufficientStorageExeception;
import com.fineias.marketplace.product.core.model.Product;
import com.fineias.marketplace.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductGateway {

    @Autowired
    private ProductService productService;

    public Product getProductById(UUID productId) {
        return productService.findProductById(productId);
    }

    public void verifyProductStorage(UUID productId, int requiredQuantity) {
       if (productService.findProductById(productId).getStorage() < requiredQuantity) {
           throw new ProductInsufficientStorageExeception();
       }
    }

}
