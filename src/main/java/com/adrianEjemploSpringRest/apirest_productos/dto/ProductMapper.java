package com.adrianEjemploSpringRest.apirest_productos.dto;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(
            Product product
    ) {

        return new ProductDto(

                product.getId(),

                product.getName(),

                product.getPrice()

        );

    }

}