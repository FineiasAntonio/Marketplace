package com.fineias.marketplace.user.service;

import com.fineias.marketplace.user.core.model.Cart;
import com.fineias.marketplace.user.core.model.CartItem;
import com.fineias.marketplace.user.core.model.User;
import com.fineias.marketplace.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getAuthenticatedUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;

        } else {
            throw new RuntimeException("Principal isn't a instance of User");
        }
    }

    public List<User> get(){
        return userRepository.findAll();
    }

}
