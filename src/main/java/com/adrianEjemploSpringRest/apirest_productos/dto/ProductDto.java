package com.adrianEjemploSpringRest.apirest_productos.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(

        Integer id,

        String name,

        String description,

        BigDecimal price,

        BrandDTO brand,

        CategoryDTO category,

        List<ProductImageDTO> images
) {
}