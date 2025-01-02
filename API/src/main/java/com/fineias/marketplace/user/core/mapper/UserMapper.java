package com.fineias.marketplace.user.core.mapper;

import com.fineias.marketplace.user.core.dto.UserDetailsDTO;
import com.fineias.marketplace.user.core.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDetailsDTO toDTO(User user) {
        return new UserDetailsDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );
    }

}
