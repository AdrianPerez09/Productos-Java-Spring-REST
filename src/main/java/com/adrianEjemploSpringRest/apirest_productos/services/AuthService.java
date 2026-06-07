package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.config.JwtService;
import com.adrianEjemploSpringRest.apirest_productos.dto.AuthResponse;
import com.adrianEjemploSpringRest.apirest_productos.dto.LoginRequest;
import com.adrianEjemploSpringRest.apirest_productos.dto.RegisterRequest;
import com.adrianEjemploSpringRest.apirest_productos.entities.RefreshToken;
import com.adrianEjemploSpringRest.apirest_productos.entities.User;
import com.adrianEjemploSpringRest.apirest_productos.enums.Role;
import com.adrianEjemploSpringRest.apirest_productos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public void register(RegisterRequest request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {

            throw new RuntimeException("User already exists");
        }

        User user = new User();

        user.setUsername(request.username());

        user.setEmail(request.email());

        user.setPassword(passwordEncoder.encode(request.password()));

        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        System.out.println("STEP 1");

        User user = userRepository.findByUsername(request.username()).orElseThrow(() -> {

            System.out.println("USER NOT FOUND");

            return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");

        });

        System.out.println("STEP 2");

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {

            System.out.println("INVALID PASSWORD");

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");

        }

        String accessToken = jwtService.generateToken(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken.getToken(), user.getRole().name(), user.getUsername());
    }
}