package com.fineias.marketplace.user.endpoint;

import com.fineias.marketplace.user.core.dto.UserDetailsDTO;
import com.fineias.marketplace.user.core.model.User;
import com.fineias.marketplace.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController("/api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.get());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserProfile(@PathVariable UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findUserById(userId));
    }

}
