package com.adrianEjemploSpringRest.apirest_productos.controllers;

import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import com.adrianEjemploSpringRest.apirest_productos.services.IProduct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private IProduct iProduct;


    public ProductController(IProduct iProduct) {
        this.iProduct = iProduct;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return iProduct.save(product);
    }

    /**
     * GET
     * http://localhost:8080
     * @return lista todos los productos
     */
    @GetMapping
    public List<Product> findAll() {
        return iProduct.findAll();
    }

    /**
     * GET
     * http://localhost:8080/{id}
     * @return El producto con el id especificado
     */
    @GetMapping("/{id}")
    public Product findById(@PathVariable Integer id) {
        return iProduct.findById(id);
    }

    /**
     * PUT
     * http://localhost:8080/{id}
     * - Actualiza un producto por el mismo pero modificado (body)
     */
    @PutMapping
    public Product Update(@RequestBody Product product) {
        return iProduct.update(product);
    }

    /**
     * DELETE
     * http://localhost:8080/{id}
     * - Elimina un producto con el id especificado
     */
    @DeleteMapping("/{idProducto}")
    public void deleteById(@PathVariable("idProducto") Integer id) {
        iProduct.deleteById(id);
    }
}
