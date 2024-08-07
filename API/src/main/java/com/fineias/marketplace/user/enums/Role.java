package com.fineias.marketplace.user.enums;

public enum Role {

    USER("User"),
    SELLER("Seller"),
    ADMIN("Admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }

}
