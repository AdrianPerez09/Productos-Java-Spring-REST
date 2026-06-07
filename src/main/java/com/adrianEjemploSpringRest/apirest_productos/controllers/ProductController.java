package com.adrianEjemploSpringRest.apirest_productos.controllers;

import com.adrianEjemploSpringRest.apirest_productos.dto.ProductDto;
import com.adrianEjemploSpringRest.apirest_productos.entities.Product;
import com.adrianEjemploSpringRest.apirest_productos.services.IProduct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProduct iProduct;


    public ProductController(IProduct iProduct) {
        this.iProduct = iProduct;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return iProduct.save(product);
    }




    //QUERIES

    /**
     * GET
     * http://localhost:8080/products
     *
     * @return lista todos los productos
     */
    @GetMapping
    public List<Product> findAll() {
        return iProduct.findAll();
    }

    /**
     * GET
     * http://localhost:8080/products/{id}
     *
     * @return El producto con el id especificado
     */
    @GetMapping("/{id}")
    public Product findById(@PathVariable Integer id) {
        return iProduct.findById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {

        return iProduct.findProductsByCategoryId(categoryId);

    }

    @GetMapping("/brand/{id}")
    public List<Product> findByBrand(

            @PathVariable Long id

    ) {

        return iProduct.findProductsByBrandId(id);

    }

    @GetMapping("/search")
    public List<ProductDto> search(

            @RequestParam String q

    ) {

        return iProduct.searchProducts(q);

    }

    /**
     * PUT
     * http://localhost:8080/products/{id}
     * - Actualiza un producto por el mismo pero modificado (body)
     */

    //-------------------------------------------------------

    // UPDATE


    @PutMapping
    public Product Update(@RequestBody Product product) {
        return iProduct.update(product);
    }

    /**
     * DELETE
     * http://localhost:8080/products/{id}
     * - Elimina un producto con el id especificado
     */

    //----------------------------------------------------------
    @DeleteMapping("/{idProducto}")
    public void deleteById(@PathVariable("idProducto") Integer id) {
        iProduct.deleteById(id);
    }
}
