package com.adrianEjemploSpringRest.apirest_productos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a product available in the catalog.
 * Stores the basic information required for product management.
 */
@Getter
@Setter
@Entity
@Table(name = "productos")
public class Product {
    /**
     * Unique identifier of the product.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductImage> images;

    /**
     * Product name displayed to users.
     */


    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Full product description
     */

    @Column(length = 1024)
    private String description;

    /**
     * Current selling price of the product.
     */

    private BigDecimal price;

    /**
     * Current stock of the product.
     */


    @Column(nullable = false)
    private Integer stock;

    /**
     * category of the product.
     */



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     *  brand the product.
     */


    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    /**
     * Date when the product was created
     */

    @CreationTimestamp
    private LocalDateTime creationDate;

    /**
     * Date when the product was updated/modified
     */

    @UpdateTimestamp
    private LocalDateTime updateDate;
}