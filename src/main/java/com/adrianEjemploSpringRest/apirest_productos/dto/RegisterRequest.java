package com.adrianEjemploSpringRest.apirest_productos.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String username,

        @NotBlank
        String email,

        @NotBlank
        String password
) {}