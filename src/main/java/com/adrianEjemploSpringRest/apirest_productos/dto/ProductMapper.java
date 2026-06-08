package com.adrianEjemploSpringRest.apirest_productos.dto;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toDto(
            Product product
    ) {

        BrandDTO brandDTO = new BrandDTO(

                product.getBrand().getId(),

                product.getBrand().getName()

        );

        CategoryDTO categoryDTO = new CategoryDTO(

                product.getCategory().getId(),

                product.getCategory().getName()

        );

        return new ProductDto(

                product.getId(),

                product.getName(),

                product.getDescription(),

                product.getPrice(),

                brandDTO,

                categoryDTO,

                product.getImages()

                        .stream()

                        .map(image ->

                                new ProductImageDTO(

                                        image.getId(),

                                        image.getImageUrl(),

                                        image.isThumbnail()

                                )

                        )

                        .toList()



        );

    }

}