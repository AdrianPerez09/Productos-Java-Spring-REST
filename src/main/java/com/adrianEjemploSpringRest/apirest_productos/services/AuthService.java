package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.config.JwtService;
import com.adrianEjemploSpringRest.apirest_productos.dto.AuthResponse;
import com.adrianEjemploSpringRest.apirest_productos.dto.LoginRequest;
import com.adrianEjemploSpringRest.apirest_productos.dto.RegisterRequest;
import com.adrianEjemploSpringRest.apirest_productos.entities.User;
import com.adrianEjemploSpringRest.apirest_productos.enums.Role;
import com.adrianEjemploSpringRest.apirest_productos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {

        if (userRepository.findByUsername(request.username()).isPresent()) {

            throw new RuntimeException("User already exists");
        }

        User user = new User();

        user.setUsername(request.username());

        user.setPassword(passwordEncoder.encode(request.password()));

        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.username()).orElseThrow();

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}