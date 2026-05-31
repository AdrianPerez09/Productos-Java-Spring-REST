package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.entities.User;
import com.adrianEjemploSpringRest.apirest_productos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username).orElseThrow();

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().name())
                .build();
    }
}