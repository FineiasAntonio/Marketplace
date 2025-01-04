package com.fineias.marketplace.user.exception;

import com.fineias.marketplace.exception.main.NotFoundException;

public class ProductNotFoundInCartException extends NotFoundException {
    public ProductNotFoundInCartException() {
        super("Couldn't find any product with this ID");
    }
}
