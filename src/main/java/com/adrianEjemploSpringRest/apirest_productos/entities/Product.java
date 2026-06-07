package com.adrianEjemploSpringRest.apirest_productos.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Product name displayed to users.
     */
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Full product description
     */
    @Setter
    @Getter
    @Column(length = 1024)
    private String description;

    /**
     * Current selling price of the product.
     */
    @Setter
    @Getter
    private BigDecimal price;

    /**
     * Current stock of the product.
     */

    @Setter
    @Getter
    @Column(nullable = false)
    private Integer stock;

    /**
     * category of the product.
     */


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     *  brand the product.
     */

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    /**
     * Date when the product was created
     */
    @Setter
    @Getter
    @CreationTimestamp
    private LocalDateTime creationDate;

    /**
     * Date when the product was updated/modified
     */
    @Setter
    @Getter
    @UpdateTimestamp
    private LocalDateTime updateDate;
}