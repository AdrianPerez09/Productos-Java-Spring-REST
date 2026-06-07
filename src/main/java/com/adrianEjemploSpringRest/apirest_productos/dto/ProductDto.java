package com.adrianEjemploSpringRest.apirest_productos.dto;

import java.math.BigDecimal;

public record ProductDto(

        Integer id,

        String name,

        BigDecimal price

) {
}