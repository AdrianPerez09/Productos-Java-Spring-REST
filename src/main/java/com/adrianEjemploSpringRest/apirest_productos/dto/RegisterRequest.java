package com.adrianEjemploSpringRest.apirest_productos.dto;

public record RegisterRequest(
        String username,
        String password
) {}