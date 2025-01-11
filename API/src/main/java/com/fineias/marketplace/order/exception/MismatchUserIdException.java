package com.fineias.marketplace.order.exception;

import com.fineias.marketplace.exception.main.ForbiddenException;

public class MismatchUserIdException extends ForbiddenException {
    public MismatchUserIdException() {
        super("The user id provided doesn't match with authenticated user");
    }
}
