package com.adrianEjemploSpringRest.apirest_productos.dto;

public record AuthResponse(

        String accessToken,

        String refreshToken,

        String role
) {}