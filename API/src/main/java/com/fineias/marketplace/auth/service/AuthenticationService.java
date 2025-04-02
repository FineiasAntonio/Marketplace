package com.fineias.marketplace.auth.service;

import com.fineias.marketplace.auth.dto.LoginRequestDTO;
import com.fineias.marketplace.auth.dto.RegisterRequestDTO;
import com.fineias.marketplace.auth.exception.AccountAlreadyExistsException;
import com.fineias.marketplace.user.core.enums.Role;
import com.fineias.marketplace.user.core.model.Cart;
import com.fineias.marketplace.user.core.model.User;
import com.fineias.marketplace.user.repository.CartRepository;
import com.fineias.marketplace.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository, CartRepository cartRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public String registerNewUser(RegisterRequestDTO registerRequest) {

        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new AccountAlreadyExistsException();
        }

        User registeredUser = userRepository.save(
                User.builder()
                        .name(registerRequest.name())
                        .email(registerRequest.email())
                        .role(Role.USER)
                        .cart(cartRepository.save(new Cart()))
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
