package com.fineias.marketplace.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public boolean hasPermission(Authentication authentication, HttpServletRequest httpRequest) {

        if (httpRequest.getRequestURL().toString().contains("/admin")) {

            return authentication.getAuthorities()
                    .stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        } else {

            return authentication.getAuthorities()
                    .stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));

        }

    }


}
