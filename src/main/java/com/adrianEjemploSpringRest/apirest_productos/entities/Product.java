package com.adrianEjemploSpringRest.apirest_productos.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a product available in the catalog.
 * Stores the basic information required for product management.
 */

@Entity
@Table(name = "productos")
public class Product {
    /**
     * Unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Product name displayed to users.
     */
    private String name;

    /**
     * Full product description
     */
    private String description;

    /**
     * Current selling price of the product.
     */
    private BigDecimal price;

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

    public Product() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}