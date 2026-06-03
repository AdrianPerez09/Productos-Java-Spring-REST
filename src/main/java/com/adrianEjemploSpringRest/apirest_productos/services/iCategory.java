package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.entities.Category;

import java.util.List;

public interface iCategory {
    Category save(Category category);

    List<Category> findAll();
    Category findById(Integer id);

    void deleteById(Integer id);

    Category update(Category category);
}

