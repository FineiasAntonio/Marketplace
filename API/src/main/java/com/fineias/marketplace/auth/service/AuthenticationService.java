package com.fineias.marketplace.auth.service;

import com.fineias.marketplace.auth.dto.LoginRequestDTO;
import com.fineias.marketplace.auth.dto.RegisterRequestDTO;
import com.fineias.marketplace.auth.exception.AccountNotFoundException;
import com.fineias.marketplace.user.enums.Role;
import com.fineias.marketplace.user.model.User;
import com.fineias.marketplace.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public String registerNewUser(RegisterRequestDTO registerRequest) {

        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new AccountNotFoundException();
        }

        User registeredUser = userRepository.save(
                User.builder()
                        .name(registerRequest.name())
                        .email(registerRequest.email())
                        .role(Role.USER)
                        .password(passwordEncoder.encode(registerRequest.password())).build()
        );

        return jwtService.generateTokenForNewUser(registeredUser.getUserId());

    }

    public String loginUser(LoginRequestDTO loginRequest) {
        var loginToken = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(loginToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtService.generateToken(loginRequest);
    }

}
