package com.adrianEjemploSpringRest.apirest_productos.specifications;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasName(String query) {

        return (root, criteriaQuery, criteriaBuilder) ->

                criteriaBuilder.like(

                        criteriaBuilder.lower(

                                root.get("name")

                        ),

                        "%" +

                                query.toLowerCase()

                                + "%"

                );

    }

    public static Specification<Product> hasBrand(Integer brandId) {

        return (root, query, builder) ->

                builder.equal(

                        root.get("brand").get("id"),

                        brandId

                );

    }

    public static Specification<Product>
    hasCategory(Integer categoryId) {

        return (root, query, builder) ->

                builder.equal(

                        root.get("category")
                                .get("id"),

                        categoryId

                );

    }

}