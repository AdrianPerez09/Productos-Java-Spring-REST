package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.entities.Brand;

import java.util.List;

public interface iBrand {
    Brand save(Brand brand);

    List<Brand> findAll();
    Brand findById(Integer id);

    void deleteById(Integer id);

    Brand update(Brand brand);
}

