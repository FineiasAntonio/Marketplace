package com.fineias.marketplace.user.exception;

import com.fineias.marketplace.exception.main.NotFoundException;

public class CartNotFoundException extends NotFoundException {
    public CartNotFoundException() {
        super("Couldn't find the cart with this ID");
    }
}
