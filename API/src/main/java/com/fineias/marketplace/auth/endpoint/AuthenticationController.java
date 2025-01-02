package com.fineias.marketplace.auth.endpoint;

import com.fineias.marketplace.auth.dto.LoginRequestDTO;
import com.fineias.marketplace.auth.dto.RegisterRequestDTO;
import com.fineias.marketplace.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO registerRequest) {

        String token = authenticationService.registerNewUser(registerRequest);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = authenticationService.loginUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }


}
