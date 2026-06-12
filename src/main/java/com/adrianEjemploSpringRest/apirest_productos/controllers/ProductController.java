package com.adrianEjemploSpringRest.apirest_productos.controllers;

import com.adrianEjemploSpringRest.apirest_productos.dto.ProductDto;
import com.adrianEjemploSpringRest.apirest_productos.dto.ProductMapper;
import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import com.adrianEjemploSpringRest.apirest_productos.repositories.ProductRepository;
import com.adrianEjemploSpringRest.apirest_productos.services.IProduct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProduct iProduct;

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductController(
            IProduct iProduct, ProductRepository productRepository,
            ProductMapper productMapper
    ) {

        this.iProduct = iProduct;
        this.productRepository = productRepository;

        this.productMapper = productMapper;

    }

    // ==========================================
    // CREATE
    // ==========================================

    @PostMapping
    public Product save(
            @RequestBody Product product
    ) {

        return iProduct.save(product);

    }

    // ==========================================
    // READ
    // ==========================================

    @GetMapping
    public List<ProductDto> findAll() {

        return iProduct.findAll()

                .stream()

                .map(productMapper::toDto)

                .toList();

    }

    @GetMapping("/{id}")
    public ProductDto findById(
            @PathVariable Integer id
    ) {

        Product product =
                iProduct.findById(id);

        return productMapper.toDto(product);

    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getProductsByCategory(
            @PathVariable Long categoryId
    ) {

        return iProduct.findProductsByCategoryId(categoryId)

                .stream()

                .map(productMapper::toDto)

                .toList();

    }

    @GetMapping("/brand/{id}")
    public List<ProductDto> findByBrand(
            @PathVariable Long id
    ) {

        return iProduct.findProductsByBrandId(id)

                .stream()

                .map(productMapper::toDto)

                .toList();

    }

    @GetMapping("/search")
    public List<ProductDto> search(

            @RequestParam(required = false)
            String query,

            @RequestParam(required = false)
            Integer brandId,

            @RequestParam(required = false)
            Integer categoryId,

            @RequestParam(required = false)
            String sort

    ) {

        return iProduct.searchProducts(

                query,

                brandId,

                categoryId,

                sort

        );

    }

    @GetMapping("/suggestions")
    public List<ProductDto> suggestions(

            @RequestParam String q

    ) {

        return iProduct.getSuggestions(q);

    }

    // ==========================================
    // UPDATE
    // ==========================================

    @PutMapping
    public Product update(
            @RequestBody Product product
    ) {

        return iProduct.update(product);

    }

    // ==========================================
    // DELETE
    // ==========================================

    @DeleteMapping("/{idProducto}")
    public void deleteById(
            @PathVariable("idProducto") Integer id
    ) {

        iProduct.deleteById(id);

    }

}