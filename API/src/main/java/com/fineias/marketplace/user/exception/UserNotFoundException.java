package com.fineias.marketplace.user.exception;

import com.fineias.marketplace.exception.main.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("Couldn't find a user with this ID");
    }
}
