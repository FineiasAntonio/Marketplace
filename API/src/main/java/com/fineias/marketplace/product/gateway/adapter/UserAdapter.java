package com.fineias.marketplace.product.gateway.adapter;

import com.fineias.marketplace.user.core.mapper.UserMapper;
import com.fineias.marketplace.user.core.model.User;
import com.fineias.marketplace.user.gateway.port.UserPort;
import com.fineias.marketplace.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserAdapter implements UserPort {

    @Autowired private UserRepository userRepository;
    @Autowired private UserMapper userMapper;

    @Override
    public UUID getAuthenticatedUserId() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).getUserId();

        } else {
            throw new RuntimeException("Principal isn't a instance of User");
        }
    }

}
