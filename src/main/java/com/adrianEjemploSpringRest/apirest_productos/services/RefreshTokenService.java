package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.entities.RefreshToken;
import com.adrianEjemploSpringRest.apirest_productos.entities.User;
import com.adrianEjemploSpringRest.apirest_productos.repositories.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Value("${jwt.refresh.expiration}")
    private Long refreshDuration;


    public RefreshToken createRefreshToken(User user) {

        RefreshToken token = new RefreshToken();

        token.setUser(user);

        token.setToken(UUID.randomUUID().toString());

        token.setExpiryDate(

                Instant.now().plusMillis(refreshDuration)
        );
        return repository.save(token);
    }

    public RefreshToken verifyToken(String token) {

        RefreshToken refreshToken =

                repository.findByToken(token).orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {

            repository.delete(refreshToken);

            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }
}