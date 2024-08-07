package com.fineias.marketplace.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fineias.marketplace.auth.dto.LoginRequestDTO;
import com.fineias.marketplace.user.model.User;
import com.fineias.marketplace.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService implements UserDetailsService {

    private final Algorithm ALGORITHM = Algorithm.HMAC256("hello-world");
    private final String ISSUER = "marketplace-api";

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public String generateToken(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email());

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getEmail())
                .withExpiresAt(
                        LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.UTC)
                ).sign(ALGORITHM);

    }

    public String generateTokenForNewUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("user ID wasn't created"));

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(user.getEmail())
                .withExpiresAt(
                        LocalDateTime.now().plusMonths(1).toInstant(ZoneOffset.UTC)
                ).sign(ALGORITHM);
    }

    public String validateToken(String token) {
        return JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getSubject();
    }

}
