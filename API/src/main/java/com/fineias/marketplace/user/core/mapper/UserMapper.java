package com.fineias.marketplace.user.core.mapper;

import com.fineias.marketplace.product.core.model.Product;
import com.fineias.marketplace.user.core.dto.UserDetailsDTO;
import com.fineias.marketplace.user.core.dto.UserSelledProductsDTO;
import com.fineias.marketplace.user.core.enums.Role;
import com.fineias.marketplace.user.core.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserMapper {

    public UserDetailsDTO toDTO(User user, Optional<List<UserSelledProductsDTO>> userSelledProducts) {
        return new UserDetailsDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getRole() == Role.SELLER ? userSelledProducts : Optional.empty()
        );
    }

    public List<UserSelledProductsDTO> mapToSelledProductsDTO(List<Product> userSelledProducts) {
        return userSelledProducts.stream().map(product ->
                new UserSelledProductsDTO(
                        product.getProductId(),
                        product.getProductName(),
                        product.getPrice(),
                        product.isActivated()
                )).toList();
    }

}
