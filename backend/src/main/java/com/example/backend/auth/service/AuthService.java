package com.example.backend.auth.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.example.backend.auth.dto.AuthResponse;
import com.example.backend.auth.dto.LoginRequest;
import com.example.backend.auth.entity.RefreshToken;
import com.example.backend.auth.repository.RefreshTokenRepository;
import com.example.backend.auth.security.JwtService;
import com.example.backend.auth.security.UserPrincipal;
import com.example.backend.user.entity.User;

@Service
public class AuthService {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshExpirationMs;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            RefreshTokenRepository refreshTokenRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public AuthResponse login(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        User user = principal.getUser();

        String accessToken = jwtService.generateAccessToken(principal);
        RefreshToken refreshToken = createRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken.getToken(), jwtService.getAccessExpirationMs() / 1000);
    }

    public AuthResponse refresh(String rawRefreshToken) {
        RefreshToken stored = refreshTokenRepository.findByToken(rawRefreshToken)
                .orElseThrow(() -> new BadCredentialsException("Refresh token invalide"));

        if (stored.isRevoked() || stored.isExpired()) {
            throw new BadCredentialsException("Refresh token expiré ou révoqué");
        }

        User user = stored.getUser();

        // Rotation : on invalide l'ancien refresh token et on en émet un nouveau
        stored.setRevoked(true);
        refreshTokenRepository.save(stored);

        RefreshToken newRefreshToken = createRefreshToken(user);
        UserPrincipal principal = new UserPrincipal(user);
        String accessToken = jwtService.generateAccessToken(principal);

        return new AuthResponse(accessToken, newRefreshToken.getToken(), jwtService.getAccessExpirationMs() / 1000);
    }

    public void logout(String rawRefreshToken) {
        refreshTokenRepository.findByToken(rawRefreshToken)
                .ifPresent(rt -> {
                    rt.setRevoked(true);
                    refreshTokenRepository.save(rt);
                });
    }

    private RefreshToken createRefreshToken(User user) {
        String token = generateOpaqueToken();
        Instant expiry = Instant.now().plus(refreshExpirationMs, ChronoUnit.MILLIS);
        RefreshToken refreshToken = new RefreshToken(token, user, expiry);
        return refreshTokenRepository.save(refreshToken);
    }

    private String generateOpaqueToken() {
        byte[] bytes = new byte[64];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
