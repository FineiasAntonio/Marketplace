package com.fineias.marketplace.product.exception;

import com.fineias.marketplace.exception.main.BadRequestException;

public class ProductInsufficientStorageExeception extends BadRequestException {

    public ProductInsufficientStorageExeception() {
        super("There's no sufficient storage for this product");
    }
}
