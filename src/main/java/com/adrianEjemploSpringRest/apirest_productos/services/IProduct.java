package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;

import java.util.List;

public interface IProduct {

    Product save(Product product);

    List<Product> findAll();
    Product findById(Integer id);

    void deleteById(Integer id);

    Product update(Product product);
}
