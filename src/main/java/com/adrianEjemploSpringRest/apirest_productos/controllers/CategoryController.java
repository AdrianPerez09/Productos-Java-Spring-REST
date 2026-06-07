package com.adrianEjemploSpringRest.apirest_productos.controllers;

import com.adrianEjemploSpringRest.apirest_productos.entities.Category;
import com.adrianEjemploSpringRest.apirest_productos.services.CategoryServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    // Servicio de categorías
    private final CategoryServiceImp categoryService;

    // Obtener todas las categorías
    @GetMapping
    public List<Category> getAllCategories() {

        return categoryService.findAll();

    }
}