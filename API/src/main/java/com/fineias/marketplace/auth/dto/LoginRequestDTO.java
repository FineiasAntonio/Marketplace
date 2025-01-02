package com.fineias.marketplace.auth.dto;

public record LoginRequestDTO(
        String email,
        String password
) {}
