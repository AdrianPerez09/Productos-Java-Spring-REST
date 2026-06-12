package com.adrianEjemploSpringRest.apirest_productos.specifications;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasSearchTerm(
            String query
    ) {

        String searchTerm =

                "%" +

                        query.toLowerCase()

                        + "%";

        return (

                root,
                criteriaQuery,
                criteriaBuilder

        ) ->

                criteriaBuilder.or(

                    /* ==========================
                       PRODUCT NAME
                    ========================== */

                        criteriaBuilder.like(

                                criteriaBuilder.lower(

                                        root.get("name")

                                ),

                                searchTerm

                        ),

                    /* ==========================
                       BRAND NAME
                    ========================== */

                        criteriaBuilder.like(

                                criteriaBuilder.lower(

                                        root.get("brand")
                                                .get("name")

                                ),

                                searchTerm

                        ),

                    /* ==========================
                       CATEGORY NAME
                    ========================== */

                        criteriaBuilder.like(

                                criteriaBuilder.lower(

                                        root.get("category")
                                                .get("name")

                                ),

                                searchTerm

                        ),

                        /* ==========================
                           PRODUCT Description
                        ========================== */

                        criteriaBuilder.like(

                                criteriaBuilder.lower(

                                        root.get("description")

                                ),

                                searchTerm

                        )

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