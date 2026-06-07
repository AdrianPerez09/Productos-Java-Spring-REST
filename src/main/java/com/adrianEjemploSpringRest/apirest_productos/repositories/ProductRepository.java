package com.adrianEjemploSpringRest.apirest_productos.repositories;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductsByCategoryId(Long categoryId);

    List<Product> findProductsByBrandId(Long brandId);

    List<Product> findTop10ByNameContainingIgnoreCase(String name);
}
