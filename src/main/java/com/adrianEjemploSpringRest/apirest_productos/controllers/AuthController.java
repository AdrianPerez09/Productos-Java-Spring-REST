package com.adrianEjemploSpringRest.apirest_productos.controllers;

import com.adrianEjemploSpringRest.apirest_productos.config.JwtService;
import com.adrianEjemploSpringRest.apirest_productos.dto.AuthResponse;
import com.adrianEjemploSpringRest.apirest_productos.dto.LoginRequest;
import com.adrianEjemploSpringRest.apirest_productos.dto.RefreshTokenRequest;
import com.adrianEjemploSpringRest.apirest_productos.dto.RegisterRequest;
import com.adrianEjemploSpringRest.apirest_productos.entities.RefreshToken;
import com.adrianEjemploSpringRest.apirest_productos.entities.User;
import com.adrianEjemploSpringRest.apirest_productos.services.AuthService;
import com.adrianEjemploSpringRest.apirest_productos.services.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService  jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(
            @RequestBody RefreshTokenRequest request
    ) {
        RefreshToken refreshToken = refreshTokenService.verifyToken(request.refreshToken());

        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return new AuthResponse(accessToken, refreshToken.getToken(), user.getRole().name(), user.getUsername());
    }
}
