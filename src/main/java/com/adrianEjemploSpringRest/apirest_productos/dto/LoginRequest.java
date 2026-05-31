package com.adrianEjemploSpringRest.apirest_productos.dto;

public record LoginRequest(
        String username,
        String password
) {}