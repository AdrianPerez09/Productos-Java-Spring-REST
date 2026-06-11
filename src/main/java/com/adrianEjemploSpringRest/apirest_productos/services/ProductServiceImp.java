package com.adrianEjemploSpringRest.apirest_productos.services;

import com.adrianEjemploSpringRest.apirest_productos.dto.ProductDto;
import com.adrianEjemploSpringRest.apirest_productos.dto.ProductMapper;
import com.adrianEjemploSpringRest.apirest_productos.entities.Category;
import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import com.adrianEjemploSpringRest.apirest_productos.repositories.ProductRepository;
import com.adrianEjemploSpringRest.apirest_productos.specifications.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements IProduct {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImp(ProductRepository productRepository, ProductMapper productMapper) {
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

            String query,

            Integer brandId,

            Integer categoryId,

            String sort

    ) {

        Specification<Product> specification =

                (root, criteriaQuery, criteriaBuilder) ->

                        criteriaBuilder.conjunction();


        specification = applyNameFilter(specification, query);

        specification = applyBrandFilter(specification, brandId);

        specification = applyCategoryFilter(specification, categoryId);

        List<Product> products = productRepository.findAll(specification);

        products = applySorting(products, sort);

        return products

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


    // Filtros y Sorting


    private Specification<Product> applyNameFilter(

            Specification<Product> specification,

            String query
    ) {
        if (
                query == null ||

                        query.isBlank()
        ) {
            return specification;
        }

        return specification.and(
                ProductSpecification.hasName(query)
        );

    }

    private Specification<Product> applyBrandFilter(

            Specification<Product> specification,

            Integer brandId

    ) {
        if (
                brandId == null
        ) {
            return specification;
        }
        return specification.and(

                ProductSpecification
                        .hasBrand(brandId)
        );
    }

    private Specification<Product> applyCategoryFilter(

            Specification<Product> specification,

            Integer categoryId
    ) {
        if (
                categoryId == null
        ) {
            return specification;
        }
        return specification.and(

                ProductSpecification
                        .hasCategory(categoryId)

        );
    }

    private List<Product> applySorting(

            List<Product> products,

            String sort

    ) {

        if (

                sort == null ||

                        sort.isBlank()

        ) {

            return products;

        }

        switch (sort) {

            case "price-asc" ->

                    products.sort(

                            (first, second) ->

                                    first.getPrice()

                                            .compareTo(

                                                    second.getPrice()

                                            )

                    );

            case "price-desc" ->

                    products.sort(

                            (first, second) ->

                                    second.getPrice()

                                            .compareTo(

                                                    first.getPrice()

                                            )

                    );

        }

        return products;

    }
}
