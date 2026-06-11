package com.adrianEjemploSpringRest.apirest_productos.controllers;

import com.adrianEjemploSpringRest.apirest_productos.dto.BrandDTO;
import com.adrianEjemploSpringRest.apirest_productos.entities.Brand;
import com.adrianEjemploSpringRest.apirest_productos.services.BrandServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandController {

    // Servicio de marcas
    private final BrandServiceImp brandService;

    // Obtener todas las marcas
    @GetMapping
    public List<Brand> getAllBrands() {

        return brandService.findAll();

    }


    @GetMapping("/category/{id}")
    public List<BrandDTO> findBrandsByCategory(
            @PathVariable Integer id
    ) {

        return brandService.findBrandsByCategory(id);

    }
}