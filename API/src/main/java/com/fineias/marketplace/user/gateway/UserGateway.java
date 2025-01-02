package com.fineias.marketplace.user.gateway;

import com.fineias.marketplace.user.core.dto.UserDetailsDTO;
import com.fineias.marketplace.user.core.mapper.UserMapper;
import com.fineias.marketplace.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGateway {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    public UserDetailsDTO getAuthenticatedUserDetails() {
        return userMapper.toDTO(userService.getAuthenticatedUser());
    }

}
