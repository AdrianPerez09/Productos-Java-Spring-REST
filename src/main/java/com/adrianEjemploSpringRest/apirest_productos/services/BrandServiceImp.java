package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.entities.Brand;
import com.adrianEjemploSpringRest.apirest_productos.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImp implements iBrand{

    private final BrandRepository brandRepository;

    public BrandServiceImp(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand findById(Integer id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Brand update(Brand brand) {
        Brand updatedBrand = brandRepository.findById(brand.getId()).get();
        updatedBrand.setName(brand.getName());
        return brandRepository.save(updatedBrand);
    }
}
