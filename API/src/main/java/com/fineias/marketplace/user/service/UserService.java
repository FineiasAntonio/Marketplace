package com.fineias.marketplace.user.service;

import com.fineias.marketplace.product.gateway.port.ProductPort;
import com.fineias.marketplace.user.core.dto.UserDetailsDTO;
import com.fineias.marketplace.user.core.dto.UserSelledProductsDTO;
import com.fineias.marketplace.user.core.mapper.UserMapper;
import com.fineias.marketplace.user.core.model.User;
import com.fineias.marketplace.user.exception.UserNotFoundException;
import com.fineias.marketplace.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private UserMapper userMapper;
    @Autowired private ProductPort productPort;

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

    public UserDetailsDTO findUserById(UUID userId) {
        return userMapper.toDTO(
                userRepository.findById(userId).orElseThrow(UserNotFoundException::new),
                Optional.of(userMapper.mapToSelledProductsDTO(productPort.getUserSelledProducts(userId)))
        );
    }

    public List<UserSelledProductsDTO> getUserSelledProducts(UUID userId) {
        return userMapper.mapToSelledProductsDTO(productPort.getUserSelledProducts(userId));
    }

}
