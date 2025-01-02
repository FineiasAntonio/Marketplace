package com.fineias.marketplace.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductInsufficientStorageExeception extends RuntimeException {

    public ProductInsufficientStorageExeception() {
        super("There's no sufficient storage for this product");
    }
}
