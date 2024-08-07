package com.fineias.marketplace.user.service;

import com.fineias.marketplace.user.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    User getUserByID(UUID userId);

}
