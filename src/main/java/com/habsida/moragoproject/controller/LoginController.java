package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.RefreshTokenResponse;
import com.habsida.moragoproject.security.JwtUtil;
import com.habsida.moragoproject.service.RefreshTokenService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    private RefreshTokenService refreshTokenService;

    public LoginController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public RefreshTokenResponse login(@Argument String username, @Argument String password) {
        try {
            RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);
            refreshTokenResponse.setJwtToken(jwtUtil.generateToken(userDetails.getUsername()));
            refreshTokenResponse.setRefreshToken(refreshToken.getToken());
            return refreshTokenResponse;
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    @MutationMapping
    @PreAuthorize("isAnonymous()")
    public String refreshToken(@Argument String token){
        RefreshToken refreshToken = refreshTokenService.findByToken(token)
                .orElseThrow(()->new RuntimeException("Refresh token is not in database!"));
        refreshTokenService.verifyExpiration(refreshToken);
        User user = refreshToken.getUser();
        return jwtUtil.generateToken(user.getFirstName());
    }
}