package com.fineias.marketplace.auth.dto;

public record RegisterRequestDTO(
        String name,
        String email,
        String password
    ) {
}
