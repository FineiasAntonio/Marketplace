package com.fineias.marketplace.product.gateway.port;

import com.fineias.marketplace.product.core.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductPort {

    Product getProductById(UUID productId);

    void verifyProductStorage(UUID productId, int requiredQuantity);

    List<Product> getUserSelledProducts(UUID userId);

}
