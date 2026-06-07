package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.dto.ProductDto;
import com.adrianEjemploSpringRest.apirest_productos.dto.ProductMapper;
import com.adrianEjemploSpringRest.apirest_productos.entities.Category;
import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import com.adrianEjemploSpringRest.apirest_productos.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements IProduct {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImp(ProductRepository productRepository, ProductMapper productMapper)
    {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        return productRepository.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findProductsByBrandId(Long brandId) {
        return productRepository.findProductsByBrandId(brandId);
    }

    @Override
    public List<ProductDto> searchProducts(
            String query
    ) {

        return productRepository
                .findTop10ByNameContainingIgnoreCase(query)
                .stream()
                .map(productMapper::toDto)
                .toList();

    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        //Buscamos el producto a modificar por id
        Product productDb = productRepository.findById(product.getId()).get();

        // Datos Modificados por el usuario
        productDb.setName(product.getName());
        productDb.setDescription(product.getDescription());
        productDb.setPrice(product.getPrice());
        productDb.setStock(product.getStock());
        productDb.setCategory(product.getCategory());
        productDb.setBrand(product.getBrand());

        return productRepository.save(productDb);
    }
}
