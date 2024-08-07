package com.fineias.marketplace.auth.filter;

import com.fineias.marketplace.auth.service.AuthorizationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AuthorizationService authorizationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if((request.getRequestURL() != null) && !authorizationService.hasPermission(authentication, request)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acess Denied");
        }

        filterChain.doFilter(request, response);
    }

}
