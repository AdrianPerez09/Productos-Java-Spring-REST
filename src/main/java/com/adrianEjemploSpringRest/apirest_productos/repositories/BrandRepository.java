package com.adrianEjemploSpringRest.apirest_productos.repositories;

import com.adrianEjemploSpringRest.apirest_productos.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {

    @Query("""

SELECT DISTINCT p.brand

FROM Product p

WHERE p.category.id = :categoryId

""")
    List<Brand> findBrandsByCategoryId(
            Integer categoryId
    );

}
