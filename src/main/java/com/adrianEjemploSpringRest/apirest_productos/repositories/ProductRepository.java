package com.adrianEjemploSpringRest.apirest_productos.repositories;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findProductsByCategoryId(Long categoryId);

    List<Product> findProductsByBrandId(Long brandId);

    List<Product> findTop10ByNameContainingIgnoreCase(String name);
}
