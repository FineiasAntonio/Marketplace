package com.fineias.marketplace.user.core.dto;

import java.util.UUID;

public record UserDetailsDTO(
        UUID userId,
        String name,
        String email
) {
}
