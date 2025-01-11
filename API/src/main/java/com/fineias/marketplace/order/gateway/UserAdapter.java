package com.fineias.marketplace.order.gateway;

import com.fineias.marketplace.user.core.model.User;
import com.fineias.marketplace.user.gateway.port.UserPort;
import com.fineias.marketplace.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAdapter implements UserPort {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return userRepository.findById(((User) principal).getUserId()).get();

        } else {
            throw new RuntimeException("Principal isn't a instance of User");
        }
    }

}
