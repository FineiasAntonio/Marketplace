package com.fineias.marketplace.user.core.dto;

import com.fineias.marketplace.user.core.enums.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public record UserDetailsDTO(
        UUID userId,
        String name,
        String email,
        Role userRole,
        Optional<List<UserSelledProductsDTO>> productsSelled
) {
}
